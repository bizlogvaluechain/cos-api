package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.SOP_TAT.subLists.MajorActivites;
import com.bizlog.rms.dto.SOP_TAT.subLists.MinorActivites;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SOPActivityDTO extends BaseDTO {

    private List<MajorActivites> majorActivites;

    private List<MinorActivites> minorActivites;

}
