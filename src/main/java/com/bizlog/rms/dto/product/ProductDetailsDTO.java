package com.bizlog.rms.dto.product;

import com.bizlog.rms.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDetailsDTO extends BaseDTO {
    @JsonProperty("Product_Info_Id")
    private Long productInfoId;
    private String skuName;
    private String skuBarcode;
    private String length;
    private String breath;
    private String height;
    private String actualWeight;
}
