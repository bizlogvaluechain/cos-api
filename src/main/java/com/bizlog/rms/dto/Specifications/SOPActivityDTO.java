package com.bizlog.rms.dto.Specifications;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SOPActivityDTO extends BaseDTO {

    private List<SOPSpecificationDTO> activityStartDate;
    private List<SOPSpecificationDTO> activityEndDate;
    private List<SOPSpecificationDTO> activityDetail;
    private List<SOPSpecificationDTO> volumeOfTicketsPerSowSop;
    private List<SOPSpecificationDTO> volumeOfProductsPerSowSop;
//    private Date activityStartDate;
//    private Date activityEndDate;
//    private String activityDetail;
//    private String volumeOfTicketsPerSowSop;
//    private String volumeOfProductsPerSowSop;
}
