package com.bizlog.rms.dto.frequency;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.sop.frequency.HolidayApplicable;
import lombok.Data;

import java.util.List;

@Data
public class FrequencyDTO extends BaseDTO {
    private String ticketsVolume;
    private Long onboardingDate;
    private Long dayStartTime;
    private Long dayEndTime;
    private List<HolidayApplicable> holidayApplicable;
    private Boolean onlyWorkdays;
}
