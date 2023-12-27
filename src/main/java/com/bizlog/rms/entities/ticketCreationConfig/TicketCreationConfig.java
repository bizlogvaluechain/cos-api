package com.bizlog.rms.entities.ticketCreationConfig;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "ticket_creation_config")
@Data
public class TicketCreationConfig extends BaseClientEntity {
    @Embedded
    private TicketCreationThrough ticketCreationThrough;
    @Embedded
    private TicketCreationBasedOn ticketCreationBasedOn;
    private String city;
    private String clientName;
    private String phoneNumber;
    private String pincode;
    private String address;
}
