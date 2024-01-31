package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sop_location_tbl")
@Data
public class LocationSop extends BaseClientEntity {
    @Column(name = "locationServed", nullable = false)
    private String locationServed;
    @Column(name = "nonServiceableArea")
    private Boolean nonServiceableArea;
    @Column(name = "IsPincodeServedBasedOnProduct")
    private Boolean IsPincodeServedBasedOnProduct;
}
