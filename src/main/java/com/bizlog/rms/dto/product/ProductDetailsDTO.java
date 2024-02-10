package com.bizlog.rms.dto.product;

import lombok.Data;

@Data
public class ProductDetailsDTO {
    public Long id;
    private String skuName;
    private String skuBarcode;
    private String length;
    private String breadth;
    private String height;
    private String actualWeight;
    private String volumetricWeight;
}
