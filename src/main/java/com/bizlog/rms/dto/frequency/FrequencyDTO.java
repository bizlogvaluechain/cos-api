package com.bizlog.rms.dto.frequency;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.frequency.HolidayApplicable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class FrequencyDTO extends BaseDTO {
    private String ticketsVolume;
    private Date onboardingDate;
    private LocalDateTime dayStartTime;
    private LocalDateTime dayEndTime;
    private List<HolidayApplicable> holidayApplicable;
    private List<Boolean> onlyWorkdays;
}
