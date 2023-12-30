package com.bizlog.rms.dto.clientinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAddressDTO {
    private Long id;
    private String country;
    private String state;
    private String city;
    private String pincode;
}
