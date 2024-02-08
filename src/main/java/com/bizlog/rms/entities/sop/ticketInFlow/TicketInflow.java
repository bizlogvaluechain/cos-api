package com.bizlog.rms.entities.sop.ticketInFlow;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "ticket_creation_config")
@Data
public class TicketInflow extends BaseClientEntity {

    private List<String> ticketCreationThrough;

    private List<String> ticketType;

}
