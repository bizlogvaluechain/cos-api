package com.bizlog.rms.entities.product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PackingMaterial_tbl")
public class PackingMaterial {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "isPackingMaterialRequiredForward", nullable = false)
    private Boolean isPackingMaterialRequiredForward;
    @Column(name = "isPackingMaterialRequiredReverse", nullable = false)
    private Boolean isPackingMaterialRequiredReverse;
    @Column(name = "packingMaterialName", nullable = false)
    private String packingMaterialName;
    @Column(name = "providerForward")
    private String providerForward;
    @Column(name = "providerReverse")
    private String providerReverse;

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
