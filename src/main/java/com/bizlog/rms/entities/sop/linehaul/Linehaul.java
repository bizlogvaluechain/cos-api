package com.bizlog.rms.entities.sop.linehaul;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "linehaul_tbl")
@Data
@Audited
public class Linehaul extends BaseClientEntity {

    @Column(name = "linehaulBy", nullable = false)
    private String linehaulBy;
    @Column(name = "LogisticsMode")
    private String logisticsMode;
    @Column(name = "LogisticsProvider")
    private String logisticsProvider;
    @Column(name = "vehicleType")
    private String vehicleType;
    @Column(name = "consolidationRequired", nullable = false)
    private Boolean consolidationRequired;
    @Column(name = "Duration")
    private Long duration;
    @Column(name = "size")
    private Long size;

}
