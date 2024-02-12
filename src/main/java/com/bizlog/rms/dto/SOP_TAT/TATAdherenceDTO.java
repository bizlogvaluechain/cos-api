package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TATAdherenceDTO extends BaseDTO {
    private Boolean tatAdherenceRequired;
    private String bizlog;
    private String customer;
    private String thirdPartyLogistics;
    private String unavoidableCircumstances;
}
