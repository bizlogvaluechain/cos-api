package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class LocationSopDTO extends BaseDTO {

    private String locationServed;
    private Boolean nonServiceableArea;
    private Boolean IsPincodeServedBasedOnProduct;
}
