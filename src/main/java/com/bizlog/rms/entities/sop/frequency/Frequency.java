package com.bizlog.rms.entities.sop.frequency;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
@Table(name = "frequency")
public class Frequency extends BaseClientEntity {
    @Column(name = "FrequencyUnit", nullable = false)
    private String frequencyUnit;
    @Column(name = "frequency", nullable = false)
    private Long frequency;
    @Column(name = "activityStartDate", nullable = false)
    private Long activityStartdate;
    @Column(name = "activityEndDate", nullable = false)
    private Long activityEndDate;
    @Column(name = "operationStartTime", nullable = false)
    private Long operationStartTime;
    @Column(name = "operationEndTime", nullable = false)
    private Long operationEndTime;
    @Column(name = "operationDays", nullable = false)
    private Long operationDays;
    @Column(name = "operationsOnbizlogHolidays", nullable = false)
    private Boolean operationsOnbizlogHolidays;
    @Column(name = "operatoinsOnClientHolidays", nullable = false)
    private Boolean operatoinsOnClientHolidays;
    @Column(name = "operationsOnStatutoryHolidays", nullable = false)
    private Boolean operationsOnStatutoryHolidays;
}
