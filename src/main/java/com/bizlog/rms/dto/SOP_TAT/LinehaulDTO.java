package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinehaulDTO extends BaseDTO {
    private String linehaulPreference;
    private String LogisticMode;
    private String LogisticProvider;
    private String vehicleType;
    private Boolean isConsolodationRequired;
    private String duration;
    private String size;
}
