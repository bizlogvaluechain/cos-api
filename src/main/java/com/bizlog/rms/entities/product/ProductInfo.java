package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ProductInfo _table")
public class ProductInfo extends BaseClientEntity {
    @Column(name = "product", nullable = false)
    private String product;
    @Column(name = "categories", nullable = false)
    private String categories;
    @Column(name = "subCategories", nullable = false)
    private String subCategories;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productDetailsId", referencedColumnName = "id")
    private ProductDetails productDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productEvaluationsId", referencedColumnName = "id")
    private ProductEvalutions productEvalutions;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "packingMaterialId", referencedColumnName = "id")
    private PackingMaterial packingMaterial;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productVehicleId", referencedColumnName = "id")
    private ProductVehicle productVehicle;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productLocationCenterId", referencedColumnName = "id")
    private ProductLocationCenter productLocationCenter;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "inventoryMaintenanceId", referencedColumnName = "id")
    private InventoryMaintenance inventoryMaintenance;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productServiceId", referencedColumnName = "id")
    private ProductService productService;
}
