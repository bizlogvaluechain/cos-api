package com.bizlog.rms.dto.TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class TATDTO extends BaseDTO {
    private Boolean isInterCityTAT;
    private Boolean isIntraCityTAT;
    private String interCityPickup;
    private Long interCityDaysPickup;
    private String interCityDrop;
    private Long interCityDaysDrop;
    private String intraCityPickup;
    private Long intraCityDaysPickup;
    private String intraCityDrop;
    private Long intraCityDaysDrop;
    private Boolean ticketInLineHaul;
    private Long lineHaulDays;
}
