package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ActivitySOPDTO extends BaseDTO {
    private List<String> miles;
    private List<String> majorActivities;
    private List<String> minorActivities;
}
