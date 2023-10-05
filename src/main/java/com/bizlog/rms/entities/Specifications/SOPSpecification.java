package com.bizlog.rms.entities.Specifications;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sop_specification")
@Data
public class SOPSpecification extends BaseClientEntity {
    private String firstMile;
    private String midMile;
    private String lastMile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sop_activity_id")
    private SOPActivity sopActivity;

}
