package com.bizlog.rms.entities.location;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Charge {
    private Long deliverableArea;
    private Long nonDeliverableArea;
    private Long outDeliverableArea;
}
