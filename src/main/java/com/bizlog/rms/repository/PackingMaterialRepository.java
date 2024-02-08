package com.bizlog.rms.repository;

import com.bizlog.rms.entities.product.PackingMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackingMaterialRepository extends JpaRepository<PackingMaterial, Long> {
}
