package com.bizlog.rms.dto.product;

import lombok.Data;

@Data
public class PackingMaterialDTO {
    public Long id;
    private Boolean isPackingMaterialRequiredForward;
    private Boolean isPackingMaterialRequiredReverse;
    private String packingMaterialName;
    private String forwardPackingMaterial;
    private String reversePackingMaterial;
}
