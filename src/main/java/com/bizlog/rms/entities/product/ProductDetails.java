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
    @Column(name = "breath", nullable = false)
    private String breath;
    @Column(name = "height", nullable = false)
    private String height;
    @Column(name = "actualWeight", nullable = false)
    private String actualWeight;

    // @JsonIgnore
    // @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinColumn(name = "Product_Info_Id", nullable = false, updatable = false)
    // private ProductInfo productInfo;
    //
    // @JsonProperty("Product_Info_Id")
    // public Long getProductInfoId() {
    // return (productInfo != null) ? productInfo.getId() : null;
    // }

}
