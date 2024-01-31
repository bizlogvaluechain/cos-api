package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreationConfigDTO extends BaseDTO {

    private List<String> ticketCreationThrough;
    private List<String> ticketCreationBasedOn;
}
