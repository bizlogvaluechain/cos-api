package com.bizlog.rms.entities.product;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "ProductEvalutions_table")
public class ProductEvalutions {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "question", nullable = false)
    private List<String> question;
    @Column(name = "fragile", nullable = false)
    private Boolean fragile;
    @Column(name = "compressed", nullable = false)
    private Boolean compressed;
    @Column(name = "isColdStorageNeeded", nullable = false)
    private Boolean storage;
    @Column(name = "isLogisticRequired", nullable = false)
    private Boolean logistic;
    @Column(name = "isInsured", nullable = false)
    private Boolean isInsured;
    @Column(name = "insuranceName")
    private String insuranceName;
    @Column(name = "policyNo")
    private String policy;
    @Column(name = "productValue")
    private String productValue;
    @Column(name = "isAccessories", nullable = false)
    private Boolean isAccessories;
    @Column(name = "length")
    private String length;
    @Column(name = "breadth")
    private String breadth;
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
