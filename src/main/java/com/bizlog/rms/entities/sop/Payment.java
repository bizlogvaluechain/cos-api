package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Table(name = "payment_tbl")
@Audited
@Data
public class Payment extends BaseClientEntity {
    @Column(name = "collectRequired", nullable = false)
    private Boolean collectRequired;
    @Column(name = "paymentMethods")
    private List<String> paymentMethods;
    @Column(name = "reconciliationFrequency")
    private Long reconclliationFrequency;

}
