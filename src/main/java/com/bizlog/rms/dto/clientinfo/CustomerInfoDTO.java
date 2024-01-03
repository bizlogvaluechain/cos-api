package com.bizlog.rms.dto.clientinfo;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.clientinfo.BillingInfo;
import com.bizlog.rms.entities.clientinfo.CompanyContactDetails;
import com.bizlog.rms.entities.clientinfo.ShipmentInfo;
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
    private String gstS3Key;
    private String panOrAadharS3Key;
    private BillingInfo billingInfo;
    private ShipmentInfo shippingAddress;
    private CompanyContactDetails companyContactDetails;
}
