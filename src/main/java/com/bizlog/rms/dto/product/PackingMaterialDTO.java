package com.bizlog.rms.dto.product;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class PackingMaterialDTO extends BaseDTO {
    private Boolean isPackingMaterialRequiredForward;
    private Boolean isPackingMaterialRequiredReversed;
    private String packingMaterialName;
}
