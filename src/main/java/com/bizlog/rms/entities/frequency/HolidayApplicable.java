package com.bizlog.rms.entities.frequency;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class HolidayApplicable {
    private Boolean publicHolidays;
    private Boolean bizlogHolidays;
    private Boolean clientHolidaays;
}
