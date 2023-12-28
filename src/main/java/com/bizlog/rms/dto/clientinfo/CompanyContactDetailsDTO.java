package com.bizlog.rms.dto.clientinfo;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyContactDetailsDTO extends BaseDTO {
    private String phoneNumber;
    private String emailId;
    private String website;
    private String socialMediaLink;
}
