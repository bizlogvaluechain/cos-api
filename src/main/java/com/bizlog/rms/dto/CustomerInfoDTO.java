package com.bizlog.rms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoDTO extends BaseDTO {
    private String clientName;
    private String legalEntityType;
    private String clientBrandName;
    private String clientSector;
    private Integer companySize;
    private String registeredYear;
    private Long companyRevenue;
    private Long clientRevenueFY;
    private String gst;
    private String panOrAadhar;
    @JsonIgnore
    private String gstS3Key;
    @JsonIgnore
    private String panOrAadharS3Key;
}
