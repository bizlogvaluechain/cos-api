package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.sop.packing.PackingRequired;
import com.bizlog.rms.entities.sop.packing.PackingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackingDTO extends BaseDTO {
    private List<PackingRequired> packingRequired;
    private List<PackingType> packingTypes;
}
