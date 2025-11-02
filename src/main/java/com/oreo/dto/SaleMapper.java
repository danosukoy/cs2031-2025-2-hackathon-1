package com.oreo.dto;

import com.oreo.domain.Sale;
import com.oreo.domain.User;
import com.oreo.dto.SaleRequestDto;
import com.oreo.dto.SaleResponseDto;

public class SaleMapper {

    public static Sale toEntity(SaleRequestDto dto, User user) {
        Sale sale = new Sale();
        sale.setAmount(dto.getAmount());
        sale.setProduct(dto.getProduct());
        sale.setUser(user);
        return sale;
    }

    public static SaleResponseDto toDto(Sale sale) {
        SaleResponseDto dto = new SaleResponseDto();
        dto.setId(sale.getId());
        dto.setAmount(sale.getAmount());
        dto.setProduct(sale.getProduct());
        dto.setUserId(sale.getUser().getId());
        return dto;
    }
}