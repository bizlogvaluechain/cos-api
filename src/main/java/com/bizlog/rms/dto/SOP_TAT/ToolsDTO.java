package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.sop.labourtoolvechile.EquipmentDetails;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ToolsDTO extends BaseDTO {
    private Boolean toolsRequired;
    private List<EquipmentDetails> equipmentDetails;

}
