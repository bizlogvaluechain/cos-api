package com.bizlog.rms.entities.clientinfo;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client_billing_info_table")
public class BillingInfo extends BaseClientEntity {
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "pincode", nullable = false)
    private String pincode;
}
