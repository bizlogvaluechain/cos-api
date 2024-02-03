package com.bizlog.rms.entities.TAT;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Tat_tbl")
public class TAT extends BaseClientEntity {
    @Column(name = "isInterCityTAT")
    private Boolean isInterCityTAT;
    @Column(name = "isIntraCityTAT")
    private Boolean isIntraCityTAT;
    @Column(name = "sameDayPickup")
    private String sameDayPickup;
    @Column(name = "nextDayPickup")
    private  String nextDayPickup;
    @Column(name = "dateForPickup")
    private Long dateForPickup;
    @Column(name = "sameDayDrop")
    private String sameDayDrop;
    @Column(name = "nextDayDrop")
    private  String nextDayDrop;
    @Column(name = "dateForDrop")
    private Long dateForDrop;
    @Column(name = "ticketInLineHaul")
    private String ticketInLineHaul;
}
