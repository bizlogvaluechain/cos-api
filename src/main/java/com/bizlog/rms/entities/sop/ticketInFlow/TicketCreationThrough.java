package com.bizlog.rms.entities.sop.ticketInFlow;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TicketCreationThrough {
    private String excel;
    private String api;
    private String form;
}
