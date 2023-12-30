package com.bizlog.rms.dto.clientengagement;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEngagementDTO extends BaseDTO {

    private String areaOfOperationLocation;
    private String onBoardingService;
    private String noOfOperationLocation;
}
