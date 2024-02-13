package com.bizlog.rms.dto.frequency;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class FrequencyDTO extends BaseDTO {

    private Long frequency;
    private String frequencyUnit;
    private Long activityStartdate;
    private Long activityEndDate;
    private Long operationStartTime;
    private Long operationEndTime;
    private Long operationDays;
    private Boolean operationsOnbizlogHolidays;
    private Boolean operatoinsOnClientHolidays;
    private Boolean operationsOnStatutoryHolidays;
}
