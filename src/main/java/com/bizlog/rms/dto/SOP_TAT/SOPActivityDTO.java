package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.SOP_TAT.subLists.MajorActivites;
import com.bizlog.rms.dto.SOP_TAT.subLists.MinorActivites;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SOPActivityDTO extends BaseDTO {

    @NotEmpty(message = "majorActivites should not be empty")
    private List<MajorActivites> majorActivites;

    @NotEmpty(message = "minorActivites should not be empty")
    private List<MinorActivites> minorActivites;

}
