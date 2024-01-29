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


    @Column(name = "IsTatRequired")
    private Boolean IsTatRequired;

    @Column(name = "tatBreachDueTo")
    @ElementCollection(targetClass = TATBreachDueTo.class, fetch = FetchType.EAGER)
    private List<TATBreachDueTo> tatBreachDueTo;


}
