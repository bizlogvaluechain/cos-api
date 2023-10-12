package com.bizlog.rms.dto.escalationMatrix;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EscalationMatrixDTO extends BaseDTO {
    private String opsContactInfo;
    private String itContactInfo;
    private String businessContactInfo;
    private String accountContactInfo;
    private String emergencyContactInfo;
}
