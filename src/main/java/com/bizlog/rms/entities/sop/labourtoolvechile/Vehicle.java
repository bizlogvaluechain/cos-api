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
    @Column(name = "vehicleRequired", nullable = false)
    private Boolean vehicleRequired;
    @Column(name = "typeOfVehicles")
    private List<String> typeOfVehicles;
    @Column(name = "vehicleVolume")
    private Long vehicleVolume;
    @Column(name = "vehicleForAllProducts")
    private Boolean vehicleForAllProducts;
    @Column(name = "vehicleForAllPincodes")
    private Boolean vehicleForAllPincodes;
    @Column(name = "priorApprovalForVehicle")
    private Boolean priorApprovalForVehicle;
    @Column(name = "durationOfActivity")
    private Long durationOfActivity;
}
