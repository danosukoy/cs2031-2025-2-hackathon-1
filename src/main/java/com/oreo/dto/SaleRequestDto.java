package com.oreo.dto;

import lombok.Data;

@Data
public class SaleRequestDto {
    private Long userId;
    private Double amount;
    private String product;
}