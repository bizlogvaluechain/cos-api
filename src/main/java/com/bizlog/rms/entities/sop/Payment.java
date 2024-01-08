package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payment_tbl")
@Data
public class Payment extends BaseClientEntity {

    private Boolean isCollectionRequired;

    private String paymentMethod;

    private Boolean IsPaymentRequired;
}
