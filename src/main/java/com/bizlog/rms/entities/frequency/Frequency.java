package com.bizlog.rms.entities.frequency;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "frequency")
public class Frequency extends BaseClientEntity {
    @Column(name = "ticketsVolume", nullable = false)
    private String ticketsVolume;
    @Column(name = "onboardingDate", nullable = false)
    private LocalDateTime onboardingDate;
    @Column(name = "dayStartTime", nullable = false)
    private LocalDateTime dayStartTime;
    @Column(name = "dayEndTime", nullable = false)
    private LocalDateTime dayEndTime;
    @Column(name = "holidayApplicable", nullable = false)
    @ElementCollection(targetClass = HolidayApplicable.class, fetch = FetchType.EAGER)
    private List<HolidayApplicable> holidayApplicable;
    @Column(name = "onlyWorkdays", nullable = false)
    private Boolean onlyWorkdays;
}
