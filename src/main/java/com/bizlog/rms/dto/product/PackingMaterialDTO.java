package com.bizlog.rms.dto.product;

import com.bizlog.rms.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PackingMaterialDTO extends BaseDTO {
    @JsonProperty("Product_Info_Id")
    private Long productInfoId;
    private Boolean isPackingMaterialRequiredForward;
    private Boolean isPackingMaterialRequiredReversed;
    private String packingMaterialName;
}
