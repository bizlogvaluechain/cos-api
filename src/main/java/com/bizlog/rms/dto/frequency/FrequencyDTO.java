package com.bizlog.rms.dto.frequency;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class FrequencyDTO extends BaseDTO {

    private String frequency;
    private Long frequencyUnit;
    private Long activityStartDate;
    private Long activityEndDate;
    private Long operationStartTime;
    private Long operationEndTime;
    private Long operationDay;
    private Boolean operationsOnPublicHolidays;
    private Boolean operationsOnBizlogHolidays;
    private Boolean operationsOnClientHolidays;
}
