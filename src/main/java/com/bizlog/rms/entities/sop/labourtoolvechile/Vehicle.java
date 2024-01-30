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
    @Column(name = "isVehicleRequired", nullable = false)
    private Boolean isVehicleRequired;
    @Column(name = "typeOfVehicle")
    private List<String> typeOfVehicle;
    @Column(name = "volumeOfVehicle")
    private Long volumeOfVehicle;
    @Column(name = "isVehicleRequiredAllProduct")
    private Boolean isVehicleRequiredAllProduct;
    @Column(name = "isVehicleRequiredAllPincodes")
    private Boolean isVehicleRequiredAllPincodes;
    @Column(name = "isPriorApprovalNeededBeforeUsingVehicle")
    private Boolean isPriorApprovalNeededBeforeUsingVehicle;
    @Column(name = "durationForActivity")
    private Long durationForActivity;
}
