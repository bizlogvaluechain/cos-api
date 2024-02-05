package com.bizlog.rms.dto.activity;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class MiddleMileDTO extends BaseDTO {
    private Boolean isLineHaul;
    private Boolean isFullTruckLoad;
    private Boolean isLessThanTruckLoad;
}
