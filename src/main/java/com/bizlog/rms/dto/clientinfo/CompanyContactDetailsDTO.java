package com.bizlog.rms.dto.clientinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyContactDetailsDTO {
    private String phoneNumber;
    private String emailId;
    private String website;
    private String socialMediaLink;
}
