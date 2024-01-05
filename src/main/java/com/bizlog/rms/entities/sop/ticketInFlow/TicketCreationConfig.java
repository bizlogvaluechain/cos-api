package com.bizlog.rms.entities.sop.ticketInFlow;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "ticket_creation_config")
@Data
public class TicketCreationConfig extends BaseClientEntity {
    @Column(name = "ticketCreationThrough", nullable = false)
    @ElementCollection(targetClass = TicketCreationThrough.class, fetch = FetchType.EAGER)
    private List<TicketCreationThrough> ticketCreationThrough;
    @Column(name = "ticketCreationBasedOn", nullable = false)
    @ElementCollection(targetClass = TicketCreationBasedOn.class, fetch = FetchType.EAGER)
    private List<TicketCreationBasedOn> ticketCreationBasedOn;

}
