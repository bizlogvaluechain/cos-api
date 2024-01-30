package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TATActivityDTO extends BaseDTO {
    private Boolean IsTatRequired;

    private String bizlog;
    private String customer;
    private String thirdPartyLogistics;
    private String unavoidableCircumtances;
}
