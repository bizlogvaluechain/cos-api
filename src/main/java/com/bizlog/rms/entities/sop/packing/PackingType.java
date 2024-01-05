package com.bizlog.rms.entities.sop.packing;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PackingType {
    private String defaultBizlog;
    private String ClientSpecific;
}
