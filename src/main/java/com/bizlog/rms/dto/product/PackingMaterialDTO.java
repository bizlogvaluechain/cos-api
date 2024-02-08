package com.bizlog.rms.dto.product;

import lombok.Data;

@Data
public class PackingMaterialDTO {
    public Long id;
    private Boolean isPackingMaterialRequiredForward;
    private Boolean isPackingMaterialRequiredReversed;
    private String packingMaterialName;
    private String providerForward;
    private String providerReverse;
}
