package com.bizlog.rms.entities.product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_Service_table")
public class ProductService {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "isInstallationRequired", nullable = false)
    private Boolean isInstallationRequired;
    @Column(name = "isInventoryManagementRequired", nullable = false)
    private Boolean isInventoryManagementRequired;

}
