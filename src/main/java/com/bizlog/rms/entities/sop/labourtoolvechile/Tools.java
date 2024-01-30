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
    @Column(name = "isToolsRequired", nullable = false)
    private Boolean isToolsRequired;
    @Column(name = "equipmentDetail")
    private String equipmentDetail;
    @Column(name = "equipmentProvideBy")
    private String equipmentProvideBy;
    @Column(name = "isTrainingProvide")
    private Boolean isTrainingProvide;
    @Column(name = "toolProductSpecific")
    private Boolean toolProductSpecific;
    @Column(name = "toolName")
    private String toolName;
}
