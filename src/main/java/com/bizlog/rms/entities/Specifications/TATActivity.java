package com.bizlog.rms.entities.Specifications;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TAT")
@Data
public class TATActivity extends BaseClientEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tatActivity")
    private List<TATSpecifications> intraCity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tatActivity")
    private List<TATSpecifications> outOfDelivery;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tatActivity")
    private List<TATSpecifications> nonServicibleArea;
}
