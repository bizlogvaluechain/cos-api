package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Product_Info_Id", nullable = false, updatable = false)
    private ProductInfo productInfo;

    @JsonProperty("Product_Info_Id")
    public Long getProductInfoId() {
        return (productInfo != null) ? productInfo.getId() : null;
    }
}
