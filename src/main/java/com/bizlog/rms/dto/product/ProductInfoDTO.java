package com.bizlog.rms.dto.product;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class ProductInfoDTO extends BaseDTO {
    private String product;
    private String categories;
    private String subCategories;
}
