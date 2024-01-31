package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
