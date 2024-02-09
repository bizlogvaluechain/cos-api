package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class LocationSopDTO extends BaseDTO {

    private String locationsServed;
    private Boolean isNSARequired;
    private Boolean productBasedpincodeService;
}
