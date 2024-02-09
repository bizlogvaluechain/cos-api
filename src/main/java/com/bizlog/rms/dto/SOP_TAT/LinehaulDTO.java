package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinehaulDTO extends BaseDTO {
    private String linehaulBy;
    private String LogisticsMode;
    private String LogisticsProvider;
    private String vehicleType;
    private Boolean consolidationRequired;
    private Long duration;
    private String size;
}
