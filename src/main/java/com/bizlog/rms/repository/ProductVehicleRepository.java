package com.bizlog.rms.repository;

import com.bizlog.rms.entities.product.ProductVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVehicleRepository extends JpaRepository<ProductVehicle, Long> {
}
