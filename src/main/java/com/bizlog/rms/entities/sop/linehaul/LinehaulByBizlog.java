package com.bizlog.rms.entities.sop.linehaul;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LinehaulByBizlog {

    private String thirdPL;
    private String vehicle;
}
