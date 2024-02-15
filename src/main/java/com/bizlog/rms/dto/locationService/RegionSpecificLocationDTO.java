package com.bizlog.rms.dto.locationService;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class RegionSpecificLocationDTO extends BaseDTO {
    private String country;
    private String state;
    private String City;
    private String address1;
    private String address2;
    private String pinCode;
    private String email;
    private String contact1;
    private String contact2;
}
