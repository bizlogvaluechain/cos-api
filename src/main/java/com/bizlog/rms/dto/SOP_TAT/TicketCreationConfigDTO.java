package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.sop.ticketInFlow.TicketCreationBasedOn;
import com.bizlog.rms.entities.sop.ticketInFlow.TicketCreationThrough;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreationConfigDTO extends BaseDTO {
    @ElementCollection
    @NotEmpty(message = "ticketCreationThrough should not be empty")
    private List<TicketCreationThrough> ticketCreationThrough;
    @ElementCollection
    @NotEmpty(message = "ticketCreationBasedOn should not be empty")
    private List<TicketCreationBasedOn> ticketCreationBasedOn;
}
