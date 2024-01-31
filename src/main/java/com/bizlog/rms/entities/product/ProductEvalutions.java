package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "ProductEvalutions_table")
public class ProductEvalutions extends BaseClientEntity {
    @Column(name = "question", nullable = false)
    private List<String> question;
    @Column(name = "isFragile", nullable = false)
    private Boolean isFragile;
    @Column(name = "isCompressed", nullable = false)
    private Boolean isCompressed;
    @Column(name = "isColdStorageNeeded", nullable = false)
    private Boolean isColdStorageNeeded;
    @Column(name = "isLogisticRequired", nullable = false)
    private Boolean isLogisticRequired;
    @Column(name = "isInsured", nullable = false)
    private Boolean isInsured;
    @Column(name = "insuranceName")
    private String insuranceName;
    @Column(name = "policyNo")
    private String policyNo;
    @Column(name = "productValue")
    private String productValue;
    @Column(name = "isAccessories", nullable = false)
    private Boolean isAccessories;
    @Column(name = "length")
    private String length;
    @Column(name = "breath")
    private String breath;
    @Column(name = "height")
    private String height;
    @Column(name = "isAdditionalLabourRequired", nullable = false)
    private Boolean isAdditionalLabourRequired;
    @Column(name = "noOfLabour")
    private Long noOfLabour;
    @Column(name = "isAdditionalEquipmentRequired", nullable = false)
    private Boolean isAdditionalEquipmentRequired;
    @Column(name = "noOfEquipment")
    private Long noOfEquipment;

}
