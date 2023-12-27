package com.bizlog.rms.entities.productInformation;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "product_information_tbl")
public class ProductInformation extends BaseClientEntity {
    @Column(name = "isProductInformationRequiredForTicketCreation", nullable = false)
    private Boolean isProductInformationRequiredForTicketCreation;
    @Column(name = "productCategory", nullable = false)
    private String productCategory;
    @Column(name = "productSubCategory", nullable = false)
    private String productSubCategory;
    @Embedded
    private ProductSize productSize;
    @Column(name = "isInventoryNeeded", nullable = false)
    private Boolean isInventoryNeeded;
    @Column(name = "isWareHousingNeeded", nullable = false)
    private Boolean isWareHousingNeeded;
    @Column(name = "isPackingNeeded", nullable = false)
    private Boolean isPackingNeeded;
    @Column(name = "isVehicleNeeded", nullable = false)
    private Boolean isVehicleNeeded;
}
