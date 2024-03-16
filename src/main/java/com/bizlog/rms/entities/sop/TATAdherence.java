package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "TAT_Adherence_tbl")
@Audited
@Data
public class TATAdherence extends BaseClientEntity {

    @Column(name = "tatAdherenceRequired", nullable = false)
    private Boolean tatAdherenceRequired;

    @Column(name = "BreachDueTo")
    @Embedded
    private BreachDueTo breachDueTo;

}
