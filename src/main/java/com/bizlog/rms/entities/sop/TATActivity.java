package com.bizlog.rms.entities.sop;

import com.bizlog.rms.dto.SOP_TAT.subLists.TATBreachDueTo;
import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "TAT")
@Data
public class TATActivity extends BaseClientEntity {
    @Column(name = "tatForFirstMile")
    private String tatForFirstMile;

    @Column(name = "tatForLastMile")
    private String tatForLastMile;

    @Column(name = "tatBreachDueTo")
    @ElementCollection(targetClass = TATBreachDueTo.class, fetch = FetchType.EAGER)
    private List<TATBreachDueTo> tatBreachDueTo;

    @Column(name = "tatForLinehaul")
    private String tatForLinehaul;

    @Column(name = "numberOfReshedules")
    private String numberOfReshedules;

}
