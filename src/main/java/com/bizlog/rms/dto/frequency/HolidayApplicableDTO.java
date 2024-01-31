package com.bizlog.rms.dto.frequency;

import lombok.Data;

@Data
public class HolidayApplicableDTO {
    public Long id;
    private Boolean publicHolidays;
    private Boolean bizlogHolidays;
    private Boolean clientHolidaays;
}
