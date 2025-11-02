package com.oreo.service;

import com.oreo.domain.Sale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsightService {

    private final GitHubModelsClient modelsClient;
    private final SaleService saleService;

    public String generateInsights(List<Sale> sales) {

        if (sales.isEmpty()) {
            return "No hay ventas para generar insights.";
        }

        double total = sales.stream().mapToDouble(Sale::getAmount).sum();
        long count = sales.size();

        Sale top = sales.stream()
                .max(Comparator.comparingDouble(Sale::getAmount))
                .orElse(null);

        String prompt = """
        Tengo datos de ventas:
        Total ventas: %.2f
        Número de registros: %d
        Producto más vendido: %s por %.2f

        Genera un resumen ejecutivo breve y motivador.
        """.formatted(
                total,
                count,
                top != null ? top.getProduct() : "N/A",
                top != null ? top.getAmount() : 0.0
        );

        return modelsClient.generateSummary(prompt);
    }
}
