package com.bizlog.rms.dto.clientinfo;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class FinanceContactInformationDTO extends BaseDTO {
    private String name;
    private String title;
    private String emailId;
    private String phoneNumber;
}
