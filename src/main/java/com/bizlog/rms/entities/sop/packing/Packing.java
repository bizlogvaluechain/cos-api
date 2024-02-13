package com.bizlog.rms.entities.sop.packing;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "packing_tbl")
@Data
public class Packing extends BaseClientEntity {

    @Column(name = "packingRequired",nullable = false)
    private String packingRequired;

    @Column(name = "packingType",nullable = false)
    private String packingType;
}
