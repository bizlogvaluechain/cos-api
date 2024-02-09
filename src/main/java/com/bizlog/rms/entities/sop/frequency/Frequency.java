package com.bizlog.rms.entities.sop.frequency;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "frequency")
public class Frequency extends BaseClientEntity {
    @Column(name = "Frequency", nullable = false)
    private String frequency;
    @Column(name = "frequencyUnit", nullable = false)
    private Long frequencyUnit;
    @Column(name = "activityStartDate", nullable = false)
    private Long activityStartDate;
    @Column(name = "activityEndDate", nullable = false)
    private Long activityEndDate;
    @Column(name = "operationStartTime", nullable = false)
    private Long operationStartTime;
    @Column(name = "operationEndTime", nullable = false)
    private Long operationEndTime;
    @Column(name = "operationDay", nullable = false)
    private Long operationDay;
    @Column(name = "operationsOnPublicHolidays", nullable = false)
    private Boolean operationsOnPublicHolidays;
    @Column(name = "operationsOnBizlogHolidays", nullable = false)
    private Boolean operationsOnBizlogHolidays;
    @Column(name = "operationsOnClientHolidays", nullable = false)
    private Boolean operationsOnClientHolidays;
}
