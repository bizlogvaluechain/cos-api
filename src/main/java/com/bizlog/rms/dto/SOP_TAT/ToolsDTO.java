package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class ToolsDTO extends BaseDTO {
    private Boolean toolsRequired;
    private String equipmentName;
    private String equipmentProvidedBy;
    private Boolean trainingProvided;
    private Boolean toolProductSpecific;

}
