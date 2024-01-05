package com.bizlog.rms.entities.sop.packing;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "packing")
@Data
public class Packing extends BaseClientEntity {

    @Column(name = "packingRequired")
    @ElementCollection(targetClass = PackingRequired.class, fetch = FetchType.EAGER)
    private List<PackingRequired> packingRequired;

    @Column(name = "packingType")
    @ElementCollection(targetClass = PackingType.class, fetch = FetchType.EAGER)
    private List<PackingType> packingTypes;
}
