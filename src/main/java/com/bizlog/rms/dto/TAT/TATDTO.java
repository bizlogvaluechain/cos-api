package com.bizlog.rms.dto.TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class TATDTO extends BaseDTO {

    private Boolean isInterCityTAT;
    private Boolean isIntraCityTAT;
    private String sameDayPickup;
    private  String nextDayPickup;
    private Long dateForPickup;
    private String sameDayDrop;
    private  String nextDayDrop;
    private Long dateForDrop;
    private String ticketInLineHaul;
}
