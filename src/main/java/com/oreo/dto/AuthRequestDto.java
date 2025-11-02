package com.oreo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AuthRequestDto {
    private String username;
    private String password;
}