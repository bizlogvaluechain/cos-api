package com.bizlog.rms.entities.location;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "bizlog_locations_tbl")
public class Location extends BaseClientEntity {
    @Column(name = "bizlogLocationMaster")
    private String bizlogLocationMaster;
    @Column(name = "serviceType", nullable = false)
    @ElementCollection(targetClass = ServiceType.class, fetch = FetchType.EAGER)
    private List<ServiceType> serviceType;
    @Column(name = "charge", nullable = false)
    @ElementCollection(targetClass = Charge.class, fetch = FetchType.EAGER)
    private List<Charge> charge;
    @Column(name = "selectStates", nullable = false)
    private String selectStates;
    @Column(name = "selectCities", nullable = false)
    private String selectCities;
}
