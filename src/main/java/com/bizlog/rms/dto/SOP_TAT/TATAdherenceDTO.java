package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.sop.BreachDueTo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TATAdherenceDTO extends BaseDTO {
    private Boolean tatAdherenceRequired;
    private BreachDueTo breachDueTo;
}
