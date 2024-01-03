package com.bizlog.rms.entities.clientinfo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "client_billing_info_table")
public class BillingInfo {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "pincode", nullable = false)
    private String pincode;
}
