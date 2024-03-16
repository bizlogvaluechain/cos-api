package com.bizlog.rms.entities.productInformation;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Data
@Audited
@Table(name = "product_information_tbl")
public class ProductInformation extends BaseClientEntity {
    @Column(name = "isProductInformationRequiredForTicketCreation", nullable = false)
    private Boolean isProductInformationRequiredForTicketCreation;
    @Column(name = "productCategory", nullable = false)
    private String productCategory;
    @Column(name = "productSubCategory", nullable = false)
    private String productSubCategory;
    @Column(name = "productSize", nullable = false)
    @ElementCollection(targetClass = ProductSize.class, fetch = FetchType.EAGER)
    private List<ProductSize> productSize;
    @Column(name = "isInventoryNeeded", nullable = false)
    private Boolean isInventoryNeeded;
    @Column(name = "isWareHousingNeeded", nullable = false)
    private Boolean isWareHousingNeeded;
    @Column(name = "isPackingNeeded", nullable = false)
    private Boolean isPackingNeeded;
    @Column(name = "isVehicleNeeded", nullable = false)
    private Boolean isVehicleNeeded;
}
