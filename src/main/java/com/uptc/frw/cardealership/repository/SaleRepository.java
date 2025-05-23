package com.uptc.frw.cardealership.repository;

import com.uptc.frw.cardealership.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySellerId(long sellerId);
}
