package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sop_product_tbl")
@Data
public class Product extends BaseClientEntity {
    @Column(name = "numberOfProducts", nullable = false)
    private Long numberOfProducts;
    @Column(name = "SKUBarcodePresent", nullable = false)
    private Boolean SKUBarcodePresent;
    @Column(name = "uniqueIdentifierPresent")
    private Boolean uniqueIdentifierPresent;
    @Column(name = "uniqueIdentifier")
    private String uniqueIdentifier;

    @Column(name = "uniqueIdBarcodePresent")
    private String uniqueIdBarcodePresent;
    @Column(name = "productMetricsProvided")
    private Boolean productMetricsProvided;
    @Column(name = "inventoryManagementRequired")
    private Boolean inventoryManagementRequired;
}
