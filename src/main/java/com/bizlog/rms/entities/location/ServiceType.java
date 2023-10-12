package com.bizlog.rms.entities.location;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ServiceType {
    private String deliverableArea;
    private String nonDeliverableArea;
    private String outDeliverableArea;
}
