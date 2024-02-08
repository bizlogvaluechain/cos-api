package com.bizlog.rms.repository;

import com.bizlog.rms.entities.product.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
}
