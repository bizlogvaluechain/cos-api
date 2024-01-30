package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.sop.linehaul.LinehaulByBizlog;
import com.bizlog.rms.entities.sop.linehaul.LinehaulByClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinehaulDTO extends BaseDTO {
    private List<LinehaulByBizlog> linehaulByBizlogs;
    private List<LinehaulByClient> linehaulByClients;
    private Boolean isConsolodationRequired;
    private String duration;
    private String size;
}
