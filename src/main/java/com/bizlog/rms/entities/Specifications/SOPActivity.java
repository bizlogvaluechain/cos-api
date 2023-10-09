package com.bizlog.rms.entities.Specifications;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sop_activity")
@Data
public class SOPActivity extends BaseClientEntity {

    @Column(name = "activityStartDate",nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Date> activityStartDate;

    @Column(name = "activityEndDate",nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Date> activityEndDate;

    @Column(name = "activityDetail",nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> activityDetail;

    @Column(name = "volumeOfTicketsPerSowSop",nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> volumeOfTicketsPerSowSop;

    @Column(name = "volumeOfProductsPerSowSop",nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> volumeOfProductsPerSowSop;

    @Column(name = "operationsNeededOnOurHolidays",nullable = false)
    private Boolean operationsNeededOnOurHolidays;

    @Column(name = "operationsNeededOnYourHolidays",nullable = false)
    private Boolean operationsNeededOnYourHolidays;

    @Column(name = "operationsNeededOnStatutoryHolidays",nullable = false)
    private Boolean operationsNeededOnStatutoryHolidays;



}
