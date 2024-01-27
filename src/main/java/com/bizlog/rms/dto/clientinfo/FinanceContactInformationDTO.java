package com.bizlog.rms.dto.clientinfo;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class FinanceContactInformationDTO extends BaseDTO {
    private String contactPersonName;
    private String contactPersonTitle;
    private String contactPersonsEmailId;
    private String contactPersonsPhoneNumber;
}
