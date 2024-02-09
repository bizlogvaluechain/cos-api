package com.bizlog.rms.repository;

import com.bizlog.rms.entities.product.ProductLocationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationCenterRepository extends JpaRepository<ProductLocationCenter, Long> {
}
