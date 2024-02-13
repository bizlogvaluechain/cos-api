package com.bizlog.rms.entities.sop;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class BreachDueTo {
    private String bizlog;
    private String customer;
    private String thirdPartyLogistics;
    private String unavoidableCircumstances;
}
