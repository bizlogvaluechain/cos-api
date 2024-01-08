package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO {

    private Long noOfProduct;
    private Boolean isContainSKUBarCode;
    private Boolean isContainUniqueIdentifier;
    private Boolean isContainUniqueBarcodes;
    private Boolean isLBH;
    private Boolean IsInventoryManagementRequired;
}
