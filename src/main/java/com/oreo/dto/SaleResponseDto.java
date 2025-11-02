package com.oreo.dto;

import lombok.Data;

@Data
public class SaleResponseDto {
    private Long id;
    private Double amount;
    private String product;
    private Long userId;
}