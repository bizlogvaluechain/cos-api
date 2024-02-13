package com.bizlog.rms.entities.sop.labourtoolvechile;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Tools_tbl")
@Data
public class Tools extends BaseClientEntity {
    @Column(name = "toolsRequired", nullable = false)
    private Boolean toolsRequired;

    @Column(name = "EquipmentDetails")
    @ElementCollection(targetClass = EquipmentDetails.class, fetch = FetchType.EAGER)
    private List<EquipmentDetails> equipmentDetails;

}
