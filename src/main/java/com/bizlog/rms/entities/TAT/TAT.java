package com.bizlog.rms.entities.TAT;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
@Table(name = "Tat_tbl")
public class TAT extends BaseClientEntity {
    @Column(name = "isInterCityTAT")
    private Boolean isInterCityTAT;
    @Column(name = "isIntraCityTAT")
    private Boolean isIntraCityTAT;
    @Column(name = "interCityPickup")
    private String interCityPickup;
    @Column(name = "interCityDaysPickup")
    private Long interCityDaysPickup;
    @Column(name = "interCityDrop")
    private String interCityDrop;
    @Column(name = "interCityDaysDrop")
    private Long interCityDaysDrop;
    @Column(name = "intraCityPickup")
    private String intraCityPickup;
    @Column(name = "intraCityDaysPickup")
    private Long intraCityDaysPickup;
    @Column(name = "intraCityDrop")
    private String intraCityDrop;
    @Column(name = "intraCityDaysDrop")
    private Long intraCityDaysDrop;
    @Column(name = "ticketInLineHaul")
    private Boolean ticketInLineHaul;
    @Column(name = "lineHaulDays")
    private Long lineHaulDays;
}
