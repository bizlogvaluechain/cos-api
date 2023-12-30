package com.bizlog.rms.entities.clientengagement;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client_engagement_table")
public class ClientEngagement extends BaseClientEntity {
    @Column(name = "areaOfOperationLocation", nullable = false)
    private String areaOfOperationLocation;
    @Column(name = "onBoardingService", nullable = false)
    private String onBoardingService;
    @Column(name = "noOfOperationLocation", nullable = false)
    private String noOfOperationLocation;

}
