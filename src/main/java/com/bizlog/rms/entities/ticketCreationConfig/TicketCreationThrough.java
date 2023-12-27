package com.bizlog.rms.entities.ticketCreationConfig;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TicketCreationThrough {
    private Boolean Excel;
    private Boolean Api;
    private Boolean Form;
}
