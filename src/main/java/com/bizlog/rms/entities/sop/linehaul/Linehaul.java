package com.bizlog.rms.entities.sop.linehaul;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "linehaul_tbl")
@Data
public class Linehaul extends BaseClientEntity {

    @Column(name = "linehaulPreference", nullable = false)
    private String linehaulPreference;
    @Column(name = "LogisticMode")
    private String LogisticMode;
    @Column(name = "LogisticProvider")
    private String LogisticProvider;
    @Column(name = "vehicleType")
    private String vehicleType;
    @Column(name = "isConsolodationRequired", nullable = false)
    private Boolean isConsolodationRequired;
    @Column(name = "Duration")
    private String duration;
    @Column(name = "size")
    private String size;

}
