package com.bizlog.rms.dto.Specifications;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SOPSpecificationDTO  {
    private String firstMile;
    private String midMile;
    private String lastMile;
}
