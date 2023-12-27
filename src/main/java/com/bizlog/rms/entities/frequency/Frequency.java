package com.bizlog.rms.entities.frequency;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Entity
@Data
@Table(name = "frequency")
public class Frequency extends BaseClientEntity {
    @Column(name = "ticketsVolume", nullable = false)
    private String ticketsVolume;

    @Column(name = "onboardingDate", nullable = false)
    private long onboardingDate;

    @Column(name = "dayStartTime", nullable = false)
    private long dayStartTime;

    @Column(name = "dayEndTime", nullable = false)
    private long dayEndTime;

    @Column(name = "holidayApplicable", nullable = false)
    @ElementCollection(targetClass = HolidayApplicable.class, fetch = FetchType.EAGER)
    private List<HolidayApplicable> holidayApplicable;

    @Column(name = "onlyWorkdays", nullable = false)
    private Boolean onlyWorkdays;


    @PrePersist
    @PreUpdate
    private void updateEpochTimes() {
        onboardingDate = convertToEpochSeconds(LocalDateTime.ofEpochSecond(onboardingDate, 0, ZoneOffset.UTC));
        dayStartTime = convertToEpochSeconds(LocalDateTime.ofEpochSecond(dayStartTime, 0, ZoneOffset.UTC));
        dayEndTime = convertToEpochSeconds(LocalDateTime.ofEpochSecond(dayEndTime, 0, ZoneOffset.UTC));
    }

    private long convertToEpochSeconds(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.UTC);
    }
}
