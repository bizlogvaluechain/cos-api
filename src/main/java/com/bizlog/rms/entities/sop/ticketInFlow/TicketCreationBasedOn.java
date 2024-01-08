package com.bizlog.rms.entities.sop.ticketInFlow;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TicketCreationBasedOn {
    private String lRNumber;
    private String orderNumber;
    private String invoiceNumber;
    private String complaintNumber;
}
