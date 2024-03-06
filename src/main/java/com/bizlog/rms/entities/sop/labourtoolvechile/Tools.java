package com.bizlog.rms.entities.sop.labourtoolvechile;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Table(name = "Tools_tbl")
@Data
@Audited
public class Tools extends BaseClientEntity {
    @Column(name = "toolsRequired", nullable = false)
    private Boolean toolsRequired;

    @Column(name = "EquipmentDetails")
    @ElementCollection(targetClass = EquipmentDetails.class, fetch = FetchType.EAGER)
    private List<EquipmentDetails> equipmentDetails;

}
