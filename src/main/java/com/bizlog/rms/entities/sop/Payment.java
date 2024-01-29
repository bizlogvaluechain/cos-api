package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "payment_tbl")
@Data
public class Payment extends BaseClientEntity {
    @Column(name = "paymentMethod", nullable = false)
    private List<String> paymentMethod;
    @Column(name = "reconciliationFrequency", nullable = false)
    private String reconciliationFrequency ;
    @Column(name = "IsPaymentRequired", nullable = false)
    private Boolean IsPaymentRequired;
}
