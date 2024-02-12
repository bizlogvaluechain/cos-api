package com.bizlog.rms.dto.product;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.product.*;
import lombok.Data;
@Data
public class ProductInfoDTO extends BaseDTO {
    private String products;
    private String categories;
    private String subCategories;
    private String productName;
    private ProductDetails productDetails;
    private ProductEvalutions productEvalutions;
    private PackingMaterial packingMaterails;
    private ProductVehicle transportation;
    private ProductLocationCenter location;
    private InventoryMaintenance inventoryMaintenance;
    private ProductService productService;
}
