package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "LocationCenter_tbl")
public class LocationCenter extends BaseClientEntity {
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "address1", nullable = false)
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "pinCode", nullable = false)
    private String pinCode;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "contact1", nullable = false)
    private String contact1;
    @Column(name = "contact2")
    private String contact2;
}
