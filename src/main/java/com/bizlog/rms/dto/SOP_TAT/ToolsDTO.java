package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

@Data
public class ToolsDTO extends BaseDTO {
    private Boolean isToolsRequired;
    private String equipmentDetail;
    private String equipmentProvideBy;
    private Boolean isTrainingProvide;
    private Boolean toolProductSpecific;
    private String toolName;
}
