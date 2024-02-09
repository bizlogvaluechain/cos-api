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
    @Column(name = "toolsRequired", nullable = false)
    private Boolean toolsRequired;
    @Column(name = "equipmentName")
    private String equipmentName;
    @Column(name = "equipmentProvidedBy")
    private String equipmentProvidedBy;
    @Column(name = "trainingProvided")
    private Boolean trainingProvided;
    @Column(name = "toolProductSpecific")
    private Boolean toolProductSpecific;
}
