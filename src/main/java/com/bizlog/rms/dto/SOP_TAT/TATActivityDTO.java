package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.SOP_TAT.subLists.TATBreachDueTo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TATActivityDTO extends BaseDTO {

    private String tatForFirstMile;

    private String tatForLastMile;

    private List<TATBreachDueTo> tatBreachDueTo;

    private String tatForLinehaul;
    private String numberOfReshedules;

}
