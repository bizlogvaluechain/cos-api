package com.bizlog.rms.entities.sop.frequency;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "frequency")
public class Frequency extends BaseClientEntity {
    @Column(name = "unit", nullable = false)
    private FrequencyUnit unit;
    @Column(name = "ticketsVolume", nullable = false)
    private String  ticketsVolume;
    @Column(name = "FrequencyStartDate", nullable = false)
    private long startDate;
    @Column(name = "FrequencyEndDate", nullable = false)
    private Long endDate;
    @Column(name = "operationStartTime", nullable = false)
    private Long operationStartTime;

    @Column(name = "operationEndTime", nullable = false)
    private long operationEndTime;
    @Column(name = "operationDay", nullable = false)
    private Long operationDay;
    @Column(name = "holidayApplicable", nullable = false)
    @ElementCollection(targetClass = HolidayApplicable.class, fetch = FetchType.EAGER)
    private List<HolidayApplicable> holidayApplicable;

}
