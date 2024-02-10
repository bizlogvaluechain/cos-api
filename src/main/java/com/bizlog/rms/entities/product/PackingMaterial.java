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
    @Column(name = "forwardPackingMaterial")
    private String forwardPackingMaterial;
    @Column(name = "reversePackingMaterial")
    private String reversePackingMaterial;

}
