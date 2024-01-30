package com.bizlog.rms.entities.sop.labourtoolvechile;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "labour_tbl")
@Data
public class Labour extends BaseClientEntity {

    @Column(name = "isLabourRequired", nullable = false)
    private Boolean isLabourRequired;
    @Column(name = "noOfLabour")
    private Long noOfLabour;
    @Column(name = "isLabourRequiredParticularProducts")
    private Boolean isLabourRequiredParticularProducts;

    @Column(name = "durationForActivity")
    private Long durationForActivity;
}
