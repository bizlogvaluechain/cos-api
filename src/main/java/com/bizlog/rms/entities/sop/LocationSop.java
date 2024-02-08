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
    @Column(name = "locationsServed", nullable = false)
    private String locationsServed;
    @Column(name = "isNSARequired")
    private Boolean isNSARequired;
    @Column(name = "productBasedpincodeService")
    private Boolean productBasedpincodeService;
}
