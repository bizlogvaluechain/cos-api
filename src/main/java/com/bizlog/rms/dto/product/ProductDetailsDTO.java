package com.bizlog.rms.dto.product;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class ProductDetailsDTO extends BaseDTO {
    private String skuName;
    private String skuBarcode;
    private String length;
    private String breath;
    private String height;
    private String actualWeight;
}
