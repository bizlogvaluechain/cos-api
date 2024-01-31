package com.bizlog.rms.dto.frequency;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.sop.frequency.FrequencyUnit;
import com.bizlog.rms.entities.sop.frequency.HolidayApplicable;
import lombok.Data;


@Data
public class FrequencyDTO extends BaseDTO {
    private FrequencyUnit unit;
    private Long ticketsVolume;
    private Long startDate;
    private Long endDate;
    private Long operationStartTime;
    private Long operationEndTime;
    private Long operationDay;
    private HolidayApplicable holidayApplicable;
}
