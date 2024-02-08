package com.bizlog.rms.dto.product;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.product.*;
import lombok.Data;

@Data
public class ProductInfoDTO extends BaseDTO {
    private String product;
    private String categories;
    private String subCategories;
    private ProductDetails productDetails;
    private ProductEvalutions productEvalutions;
    private PackingMaterial packingMaterial;
    private ProductVehicle productVehicle;
    private ProductLocationCenter productLocationCenter;
    private InventoryMaintenance inventoryMaintenance;
    private ProductService productService;
}
