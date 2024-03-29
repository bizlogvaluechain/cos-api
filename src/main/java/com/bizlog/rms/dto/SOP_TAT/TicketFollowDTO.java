package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketFollowDTO extends BaseDTO {
    private String searchAndTrack;
    private Boolean clientReports;
    private Boolean liveTicketScan;
}
