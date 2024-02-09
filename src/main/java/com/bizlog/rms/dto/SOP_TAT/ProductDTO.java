package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO {
    private Long numberOfProducts;
    private Boolean SKUBarcodePresent;
    private Boolean uniqueIdentifierPresent;
    private String uniqueIdentifier;
    private String uniqueIdBarcodePresent;
    private Boolean productMetricsProvided;
    private Boolean inventoryManagementRequired;
}
