package com.bizlog.rms.entities.sop.frequency;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "frequency")
public class Frequency extends BaseClientEntity {
    @Column(name = "unit", nullable = false)
    private FrequencyUnit unit;
    @Column(name = "ticketsVolume", nullable = false)
    private Long  ticketsVolume;
    @Column(name = "FrequencyStartDate", nullable = false)
    private Long startDate;
    @Column(name = "FrequencyEndDate", nullable = false)
    private Long endDate;
    @Column(name = "operationStartTime", nullable = false)
    private Long operationStartTime;

    @Column(name = "operationEndTime", nullable = false)
    private Long operationEndTime;
    @Column(name = "operationDay", nullable = false)
    private Long operationDay;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "holidayApplicableId", referencedColumnName = "id")
    private HolidayApplicable holidayApplicable;
}
