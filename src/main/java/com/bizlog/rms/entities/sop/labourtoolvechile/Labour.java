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

    @Column(name = "manualLabourRequired", nullable = false)
    private Boolean manualLabourRequired;
    @Column(name = "labourSize")
    private Long labourSize;

    @Column(name = "priorApprovalNeedForLabour")
    private Boolean priorApprovalNeedForLabour;
    @Column(name = "labourForParticularProduct")
    private Boolean labourForParticularProduct;

    @Column(name = "durationOfActivity")
    private Long durationOfActivity;
}
