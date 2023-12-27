package com.bizlog.rms.entities.location;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Charge {
    private Long deliverableAreaCharge;
    private Long nonDeliverableAreaCharge;
    private Long outDeliverableAreaCharge;
}
