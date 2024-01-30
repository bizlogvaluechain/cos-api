package com.bizlog.rms.entities.sop.ticketInFlow;

import lombok.Data;

@Data
public class TicketCreationBasedOn {
    private String lRNumber;
    private String orderNumber;
    private String invoiceNumber;
    private String complaintNumber;
}
