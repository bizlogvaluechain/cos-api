package com.bizlog.rms.dto.SOP_TAT.subLists;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TATBreachDueTo {
    private String bizlog;
    private String customer;
    private String thirdPartyLogistics;
    private String unavoidableCircumtances;
}
