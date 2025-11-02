package com.oreo.controller;

import com.oreo.domain.Sale;
import com.oreo.domain.User;
import com.oreo.dto.SaleRequestDto;
import com.oreo.dto.SaleResponseDto;
import com.oreo.dto.SaleMapper;
import com.oreo.service.InsightService;
import com.oreo.service.SaleService;
import com.oreo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    private final UserService userService;

    @Autowired
    private InsightService insightService;

    @PostMapping
    public ResponseEntity<SaleResponseDto> createSale(@RequestBody SaleRequestDto request) {
        User user = userService.findById(request.getUserId());
        Sale sale = SaleMapper.toEntity(request, user);
        Sale saved = saleService.createSale(sale);
        return ResponseEntity.ok(SaleMapper.toDto(saved));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SaleResponseDto>> getSalesByUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        List<SaleResponseDto> sales = saleService.getSalesByUser(user)
                .stream()
                .map(SaleMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(sales);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDto>> getAllSales() {
        List<SaleResponseDto> sales = saleService.getAllSales()
                .stream()
                .map(SaleMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(sales);
    }

    @GetMapping("/insights")
    public ResponseEntity<String> getInsights() {
        List<Sale> sales = saleService.getAllSales();
        String insights = insightService.generateInsights(sales);
        return ResponseEntity.ok(insights);
    }

    @GetMapping("/insights/ai")
    public ResponseEntity<String> getInsightsAI() {
        var sales = saleService.getAllSales();
        String result = insightService.generateInsights(sales);
        return ResponseEntity.ok(result);
    }
}