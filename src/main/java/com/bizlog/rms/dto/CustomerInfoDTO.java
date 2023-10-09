package com.bizlog.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoDTO extends BaseDTO {
    private String registeredEntity;
    private String entityType;
    private String brandName;
    private String industryVertical;
    private String sector;
    private String gst;
    private String pan;
    private String msme;
}
