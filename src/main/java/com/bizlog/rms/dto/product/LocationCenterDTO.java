package com.bizlog.rms.dto.product;

import com.bizlog.rms.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationCenterDTO extends BaseDTO {
    @JsonProperty("Product_Info_Id")
    private Long productInfoId;
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
