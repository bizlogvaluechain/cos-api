package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ActivitySOPDTO extends BaseDTO {
    private List<String> Miles;
    private List<String> MajorActivity;
    private List<String> MinorActivity;
}
