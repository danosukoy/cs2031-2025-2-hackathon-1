package com.oreo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GitHubModelsClient {

    @Value("${github.models.url}")
    private String modelsUrl;

    @Value("${github.models.token}")
    private String token;

    @Value("${github.models.modelId}")
    private String modelId;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateSummary(String prompt) {

        Map<String, Object> body = Map.of(
                "model", modelId,
                "messages", List.of(
                        Map.of("role", "system", "content", "Eres un analista de datos que genera resúmenes cortos."),
                        Map.of("role", "user", "content", prompt)
                ),
                "max_tokens", 200
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        headers.set("Accept", "application/vnd.github+json");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                modelsUrl + "/inference/chat/completions",
                request,
                Map.class
        );

        Map<String, Object> choice = ((List<Map<String, Object>>)response.getBody().get("choices")).get(0);
        Map<String, Object> message = (Map<String, Object>) choice.get("message");

        return (String) message.get("content");
    }
}
