package com.bizlog.rms.entities.product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_detail_table")
public class ProductDetails {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "skuName", nullable = false)
    private String skuName;
    @Column(name = "skuBarcode", nullable = false)
    private String skuBarcode;
    @Column(name = "length", nullable = false)
    private String length;
    @Column(name = "breadth", nullable = false)
    private String breadth;
    @Column(name = "height", nullable = false)
    private String height;
    @Column(name = "actualWeight", nullable = false)
    private String actualWeight;
    @Column(name = "volumetricWeight", nullable = false)
    private String volumetricWeight;


}
