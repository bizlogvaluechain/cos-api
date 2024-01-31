package com.bizlog.rms.entities.sop.packing;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "packing")
@Data
public class Packing extends BaseClientEntity {

    @Column(name = "packingRequired",nullable = false)
    private List<String> packingRequired;

    @Column(name = "packingType",nullable = false)
    private List<String> packingTypes;
}
