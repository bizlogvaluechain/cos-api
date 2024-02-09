package com.bizlog.rms.repository;

import com.bizlog.rms.entities.product.InventoryMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMaintenanceRepository extends JpaRepository<InventoryMaintenance, Long> {
}
