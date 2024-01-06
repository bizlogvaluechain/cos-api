package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product_tbl")
@Data
public class Product extends BaseClientEntity {
    @Column(name = "noOfProduct", nullable = false)
    private Long noOfProduct;
    @Column(name = "isContainSKUBarCode", nullable = false)
    private Boolean isContainSKUBarCode;
    @Column(name = "isContainUniqueIdentifier", nullable = false)
    private Boolean isContainUniqueIdentifier;
    @Column(name = "isContainUniqueBarcodes", nullable = false)
    private Boolean isContainUniqueBarcodes;
    @Column(name = "isLBH", nullable = false)
    private Boolean isLBH;
    @Column(name = "IsInventoryManagementRequired", nullable = false)
    private Boolean IsInventoryManagementRequired;
}
