package com.bizlog.rms.entities.product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_inventory_maintenance_table")
public class InventoryMaintenance {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "isAdditionalInventory", nullable = false)
    private Boolean isAdditionalInventory;
}
