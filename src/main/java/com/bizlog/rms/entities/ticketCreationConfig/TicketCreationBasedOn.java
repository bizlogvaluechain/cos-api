package com.bizlog.rms.entities.ticketCreationConfig;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TicketCreationBasedOn {
    private String AwbNumber;
    private String OrderNumber;
    private String InvoiceNumber;
    private String ComplaintNumber;
}
