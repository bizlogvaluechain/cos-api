package com.bizlog.rms.dto.frequency;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.frequency.HolidayApplicable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FrequencyDTO extends BaseDTO {
    private String ticketsVolume;
    private LocalDateTime onboardingDate;
    private LocalDateTime dayStartTime;
    private LocalDateTime dayEndTime;
    private List<HolidayApplicable> holidayApplicable;
    private Boolean onlyWorkdays;
}
