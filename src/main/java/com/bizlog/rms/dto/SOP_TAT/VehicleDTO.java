package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO extends BaseDTO {
    private Boolean isVehicleRequired;
    private List<String> typeOfVehicle;
    private Long volumeOfVehicle;
    private Boolean isVehicleRequiredAllProduct;
    private Boolean isVehicleRequiredAllPincodes;
    private Boolean isPriorApprovalNeededBeforeUsingVehicle;
    private Long durationForActivity;
}
