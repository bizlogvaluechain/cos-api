package com.bizlog.rms.entities.sop.linehaul;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "linehaul_tbl")
@Data
public class Linehaul extends BaseClientEntity {

    @Column(name = "linehaulBy", nullable = false)
    private String linehaulBy;
    @Column(name = "LogisticsMode")
    private String LogisticsMode;
    @Column(name = "LogisticsProvider")
    private String LogisticsProvider;
    @Column(name = "vehicleType")
    private String vehicleType;
    @Column(name = "consolidationRequired", nullable = false)
    private Boolean consolidationRequired;
    @Column(name = "Duration")
    private Long duration;
    @Column(name = "size")
    private String size;

}
