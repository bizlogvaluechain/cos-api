package com.bizlog.rms.dto.product;

import lombok.Data;

@Data
public class ProductLocationCenterDTO {
    public Long id;
    private String country;
    private String state;
    private String city;
    private String address1;
    private String address2;
    private String pinCode;
    private String email;
    private String contact1;
    private String contact2;
}
