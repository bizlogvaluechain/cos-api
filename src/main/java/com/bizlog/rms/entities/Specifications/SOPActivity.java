package com.bizlog.rms.entities.Specifications;

import com.bizlog.rms.dto.SOP_TAT.subLists.MajorActivites;
import com.bizlog.rms.dto.SOP_TAT.subLists.MinorActivites;
import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sop_activity")
@Data
public class SOPActivity extends BaseClientEntity {

    @Column(name = "activityStartDate")
    private LocalDate activityStartDate;

    @Column(name = "activityEndDate")
    private LocalDate activityEndDate;

    @Column(name = "activityDetail", nullable = false)
    private String activityDetail;

    @Column(name = "majorActivites", nullable = false)
    @ElementCollection(targetClass = MajorActivites.class, fetch = FetchType.EAGER)
    private List<MajorActivites> majorActivites;

    @Column(name = "minorActivites", nullable = false)
    @ElementCollection(targetClass = MinorActivites.class, fetch = FetchType.EAGER)
    private List<MinorActivites> minorActivites;

    @Column(name = "volumeOfTicketsPerSowSop", nullable = false)
    private String volumeOfTicketsPerSowSop;

    @Column(name = "volumeOfProductsPerSowSop", nullable = false)
    private String volumeOfProductsPerSowSop;

    @Column(name = "operationsNeededOnOurHolidays", nullable = false)
    private Boolean operationsNeededOnOurHolidays;

    @Column(name = "operationsNeededOnYourHolidays", nullable = false)
    private Boolean operationsNeededOnYourHolidays;

    @Column(name = "operationsNeededOnStatutoryHolidays", nullable = false)
    private Boolean operationsNeededOnStatutoryHolidays;

}
