package com.bizlog.rms.entities.ticketCreationConfig;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TicketCreationThrough {
    private String Excel;
    private String Api;
    private String Form;
}
