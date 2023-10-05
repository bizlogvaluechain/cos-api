package com.bizlog.rms.entities.Specifications;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tat_specification")
@Data
public class TATSpecifications extends BaseClientEntity {
    private String FirstMile;

    private String MidMileByAir;
    private String MidMileBySurface;
    private String LastMile;
    @ManyToOne
    @JoinColumn(name = "tat_activity_id")
    private TATActivity tatActivity;
}
