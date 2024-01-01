package com.bizlog.rms.dto.clientinfo;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.clientinfo.BillingInfo;
import com.bizlog.rms.entities.clientinfo.CompanyContactDetails;
import com.bizlog.rms.entities.clientinfo.ShipmentInfo;
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
    private Long registeredYear;
    private Long companyRevenue;
    private String clientRevenueFY;
    private String gst;
    private String panOrAadhar;
    @JsonIgnore
    private String gstS3Key;
    @JsonIgnore
    private String panOrAadharS3Key;

    private BillingInfo billingInfo;
    private ShipmentInfo shipmentInfo;
    private CompanyContactDetails companyContactDetails;
}
