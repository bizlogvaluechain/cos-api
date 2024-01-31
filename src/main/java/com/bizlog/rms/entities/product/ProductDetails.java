package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_detail_table")
public class ProductDetails extends BaseClientEntity {
    @Column(name = "skuName", nullable = false)
    private String skuName;
    @Column(name = "skuBarcode", nullable = false)
    private String skuBarcode;
    @Column(name = "length", nullable = false)
    private String length;
    @Column(name = "breath", nullable = false)
    private String breath;
    @Column(name = "height", nullable = false)
    private String height;
    @Column(name = "actualWeight", nullable = false)
    private String actualWeight;
}
