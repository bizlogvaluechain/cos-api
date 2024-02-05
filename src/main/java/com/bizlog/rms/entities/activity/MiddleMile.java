package com.bizlog.rms.entities.activity;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "activity_Middle_Mile_tbl")
public class MiddleMile extends BaseClientEntity {
    @Column(name = "isLineHaul")
    private Boolean isLineHaul;
    @Column(name = "isFullTruckLoad")
    private Boolean isFullTruckLoad;
    @Column(name = "isLessThanTruckLoad")
    private Boolean isLessThanTruckLoad;
}
