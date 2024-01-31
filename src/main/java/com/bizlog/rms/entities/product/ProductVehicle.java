package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "product_vehicle_table")
public class ProductVehicle extends BaseClientEntity {
    @Column(name = "isBizlogVehicleNeededLinehaul", nullable = false)
    private Boolean isBizlogVehicleNeededLinehaul;
    @Column(name = "vehicleType")
    private List<String> vehicleType;
    @Column(name = "isBizlogWarehouseNeeded", nullable = false)
    private Boolean isBizlogWarehouseNeeded;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "warehouse")
    private String warehouse;
    @Column(name = "is3PLWarehouseRequired", nullable = false)
    private Boolean is3PLWarehouseRequired;
}
