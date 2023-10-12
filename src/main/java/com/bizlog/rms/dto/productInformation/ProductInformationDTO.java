package com.bizlog.rms.dto.productInformation;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.productInformation.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInformationDTO extends BaseDTO {
    private Boolean isProductInformationRequiredForTicketCreation;
    private String productCategory;
    private String productSubCategory;
    private List<ProductSize> productSize;
    private Boolean isInventoryNeeded;
    private Boolean isWareHousingNeeded;
    private Boolean isPackingNeeded;
    private Boolean isVehicleNeeded;
}
