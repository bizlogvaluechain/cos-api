package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationBasedOn;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationThrough;
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

    private TicketCreationThrough ticketCreationThrough;
    private TicketCreationBasedOn ticketCreationBasedOn;
    private String city;
    private String clientName;
    private String phoneNumber;
    private String pincode;
    private String address;
}
