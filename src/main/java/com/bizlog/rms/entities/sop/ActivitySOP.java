package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "activity_sop_tbl")
public class   ActivitySOP extends BaseClientEntity {

    @Column(name = "Miles", nullable = false)
    private List<String> miles;
    @Column(name = "MajorActivity", nullable = false)
    private List<String> majorActivities;
    @Column(name = "MinorActivity", nullable = false)
    private List<String> minorActivities;
}
