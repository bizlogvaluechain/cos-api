package com.bizlog.rms.dto.escalationMatrix;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientTechEscalationDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobile;
    private String designation;
}
