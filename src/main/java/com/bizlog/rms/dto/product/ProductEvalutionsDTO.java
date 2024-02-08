package com.bizlog.rms.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductEvalutionsDTO {
    public Long id;
    private List<String> question;
    private Boolean isFragile;
    private Boolean isCompressed;
    private Boolean isColdStorageNeeded;
    private Boolean isLogisticRequired;
    private Boolean isInsured;
    private String insuranceName;
    private String policyNo;
    private String productValue;
    private Boolean isAccessories;
    private String length;
    private String breath;
    private String height;
    private Boolean isAdditionalLabourRequired;
    private Long noOfLabour;
    private Boolean isAdditionalEquipmentRequired;
    private Long noOfEquipment;

}
