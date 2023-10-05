package com.bizlog.rms.entities.Specifications;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sop_activity")
@Data
public class SOPActivity extends BaseClientEntity {


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sopActivity")
    private List<SOPSpecification> activityStartDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sopActivity")
    private List<SOPSpecification> activityEndDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sopActivity")
    private List<SOPSpecification> activityDetail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sopActivity")
    private List<SOPSpecification> volumeOfTicketsPerSowSop;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sopActivity")
    private List<SOPSpecification> volumeOfProductsPerSowSop;


}
