package com.bizlog.rms.entities.ticketCreationConfig;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class TicketCreationBasedOn {
    private Boolean AwbNumber;
    private Boolean OrderNumber;
    private Boolean InvoiceNumber;
    private Boolean ComplaintNumber;
}
