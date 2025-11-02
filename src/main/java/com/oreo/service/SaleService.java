package com.oreo.service;

import com.oreo.domain.Sale;
import com.oreo.domain.User;
import com.oreo.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> getSalesByUser(User user) {
        return saleRepository.findByUser(user);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}