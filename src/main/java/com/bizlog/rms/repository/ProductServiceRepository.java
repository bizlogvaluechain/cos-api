package com.bizlog.rms.repository;

import com.bizlog.rms.entities.product.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductServiceRepository extends JpaRepository<ProductService, Long> {
}
