package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ProductInfo _table")
public class ProductInfo extends BaseClientEntity {
    @Column(name = "products", nullable = false)
    private String products;
    @Column(name = "categories", nullable = false)
    private String categories;
    @Column(name = "subCategories", nullable = false)
    private String subCategories;
    @Column(name = "productName")
    private String productName;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productDetailsId", referencedColumnName = "id")
    private ProductDetails productDetails;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productEvaluationsId", referencedColumnName = "id")
    private ProductEvalutions productEvalutions;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "packingMaterialId", referencedColumnName = "id")
    private PackingMaterial packingMaterails;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productVehicleId", referencedColumnName = "id")
    private ProductVehicle transportation;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productLocationCenterId", referencedColumnName = "id")
    private ProductLocationCenter location;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "inventoryMaintenanceId", referencedColumnName = "id")
    private InventoryMaintenance inventoryMaintenance;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productServiceId", referencedColumnName = "id")
    private ProductService productService;
}
