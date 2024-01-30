package com.bizlog.rms.entities.sop.linehaul;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "linehaul_tbl")
@Data
public class Linehaul extends BaseClientEntity {

    @Column(name = "linehaulByBizlogs", nullable = false)
    @ElementCollection(targetClass = LinehaulByBizlog.class, fetch = FetchType.EAGER)
    private List<LinehaulByBizlog> linehaulByBizlogs;
    @Column(name = "linehaulByClients", nullable = false)
    @ElementCollection(targetClass = LinehaulByClient.class, fetch = FetchType.EAGER)
    private List<LinehaulByClient> linehaulByClients;
    @Column(name = "isConsolodationRequired", nullable = false)
    private Boolean isConsolodationRequired;
    @Column(name = "Duration")
    private String duration;
    @Column(name = "size")
    private String size;

}
