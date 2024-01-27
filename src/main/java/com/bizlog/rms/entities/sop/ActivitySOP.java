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
public class ActivitySOP extends BaseClientEntity {

    @Column(name = "Miles", nullable = false)
    private List<String> Miles;
    @Column(name = "MajorActivity", nullable = false)
    private List<String> MajorActivity;
    @Column(name = "MinorActivity", nullable = false)
    private List<String> MinorActivity;
}
