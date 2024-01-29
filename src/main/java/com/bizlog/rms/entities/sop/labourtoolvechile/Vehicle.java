package com.bizlog.rms.entities.sop.labourtoolvechile;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "vehicle_tbl")
@Data
public class Vehicle extends BaseClientEntity {
    @Column(name = "typeOfVehicle", nullable = false)
    private List<String> typeOfVehicle;
    @Column(name = "noOfVehicle", nullable = false)
    private Long noOfVehicle;
    @Column(name = "isVehicleRequiredAllProduct", nullable = false)
    private Boolean isVehicleRequiredAllProduct;
    @Column(name = "isVehicleRequiredAllPincodes", nullable = false)
    private Boolean isVehicleRequiredAllPincodes;
    @Column(name = "isPriorApprovalNeededBeforeUsingVehicle", nullable = false)
    private Boolean isPriorApprovalNeededBeforeUsingVehicle;
    @Column(name = "durationForActivity", nullable = false)
    private Long durationForActivity;
}
