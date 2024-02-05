package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PackingMaterial_tbl")
public class PackingMaterial extends BaseClientEntity {
    @Column(name = "isPackingMaterialRequiredForward", nullable = false)
    private Boolean isPackingMaterialRequiredForward;
    @Column(name = "isPackingMaterialRequiredReversed", nullable = false)
    private Boolean isPackingMaterialRequiredReversed;
    @Column(name = "packingMaterialName", nullable = false)
    private String packingMaterialName;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Product_Info_Id", nullable = false, updatable = false)
    private ProductInfo productInfo;

    @JsonProperty("Product_Info_Id")
    public Long getProductInfoId() {
        return (productInfo != null) ? productInfo.getId() : null;
    }
}
