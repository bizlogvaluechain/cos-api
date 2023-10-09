package com.bizlog.rms.entities.Specifications;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "TAT")
@Data
public class TATActivity extends BaseClientEntity {
    @Column(name = "intraCity", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> intraCity;

    @Column(name = "outOfDelivery", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> outOfDelivery;

    @Column(name = "nonServicibleArea", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> nonServicibleArea;

}
