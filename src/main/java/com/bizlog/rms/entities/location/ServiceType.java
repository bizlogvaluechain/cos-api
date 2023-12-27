package com.bizlog.rms.entities.location;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ServiceType {
    private Boolean deliverableArea;
    private Boolean nonDeliverableArea;
    private Boolean outDeliverableArea;
}
