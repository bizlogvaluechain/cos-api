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
    @Column(name = "noOfProduct", nullable = false)
    private Long noOfProduct;
    @Column(name = "UniqueIdentifier")
    private String uniqueIdentifier;
    @Column(name = "isContainUniqueBarcodes")
    private String isContainUniqueBarcodes;
    @Column(name = "isLBH")
    private String isLBH;
    @Column(name = "IsInventoryManagementRequired")
    private String IsInventoryManagementRequired;
}
