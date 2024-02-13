package com.bizlog.rms.entities.sop.labourtoolvechile;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EquipmentDetails {

    @Column(name = "equipmentName")
    private String equipmentName;
    @Column(name = "equipmentProvidedBy")
    private String equipmentProvidedBy;
    @Column(name = "trainingProvided")
    private Boolean trainingProvided;
    @Column(name = "toolProductSpecific")
    private Boolean toolProductSpecific;
}
