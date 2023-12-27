package com.bizlog.rms.entities.location;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "bizlog_locations_tbl")
public class Location extends BaseClientEntity {
    @Column(name = "bizlogLocationMaster")
    private String bizlogLocationMaster;
    @Embedded
    private ServiceType serviceType;
    @Embedded
    private Charge charge;
    @Column(name = "selectStates", nullable = false)
    private String selectStates;
    @Column(name = "selectCities", nullable = false)
    private String selectCities;
}
