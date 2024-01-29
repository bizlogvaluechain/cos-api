package com.bizlog.rms.entities.sop.labourtoolvechile;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Tools_tbl")
@Data
public class Tools extends BaseClientEntity {
    @Column(name = "equipmentDetail", nullable = false)
    private String equipmentDetail;
    @Column(name = "equipmentProvideBy", nullable = false)
    private String equipmentProvideBy;
    @Column(name = "isTrainingProvide", nullable = false)
    private Boolean isTrainingProvide;
    @Column(name = "toolProductSpecific", nullable = false)
    private Boolean toolProductSpecific;
    @Column(name = "toolName", nullable = false)
    private String toolName;
}
