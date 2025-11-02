package com.oreo.repository;

import com.oreo.domain.Sale;
import com.oreo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByUser(User user);

}