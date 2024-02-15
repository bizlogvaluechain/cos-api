package com.bizlog.rms.entities.location;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "bizlog_locations_tbl")
public class Location extends BaseClientEntity {
    @Column(name = "countries", nullable = false)
    private List<String> countries;
    // @Column(name = "serviceType", nullable = false)
    // @ElementCollection(targetClass = ServiceType.class, fetch = FetchType.EAGER)
    // private List<ServiceType> serviceType;
    // @Column(name = "charge", nullable = false)
    // @ElementCollection(targetClass = Charge.class, fetch = FetchType.EAGER)
    // private List<Charge> charge;
    @Column(name = "states", nullable = false)
    private List<String> states;
    @Column(name = "cities", nullable = false)
    private List<String> cities;

    @Column(name = "areas", nullable = false)
    private List<String> areas;
    @Column(name = "pinCodes", nullable = false)
    private List<String> pinCodes;
    @Column(name = "transportLinehaul")
    private String transportLinehaul;
    @Column(name = "vehicle")
    private List<String> vehicle;
}
