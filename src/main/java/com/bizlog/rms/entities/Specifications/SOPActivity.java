package com.bizlog.rms.entities.Specifications;

import com.bizlog.rms.dto.SOP_TAT.subLists.MajorActivites;
import com.bizlog.rms.dto.SOP_TAT.subLists.MinorActivites;
import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Table(name = "sop_activity")
@Data
public class SOPActivity extends BaseClientEntity {

    @Column(name = "majorActivites")
    @ElementCollection(targetClass = MajorActivites.class, fetch = FetchType.EAGER)
    private List<MajorActivites> majorActivites;

    @Column(name = "minorActivites")
    @ElementCollection(targetClass = MinorActivites.class, fetch = FetchType.EAGER)
    private List<MinorActivites> minorActivites;

}
