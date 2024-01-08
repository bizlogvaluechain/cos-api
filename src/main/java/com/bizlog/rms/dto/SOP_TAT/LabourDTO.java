package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabourDTO extends BaseDTO {
    private Boolean isLabourRequired;
    private Boolean isLabourRequiredParticularProducts;
    private String durationForActivity;
}
