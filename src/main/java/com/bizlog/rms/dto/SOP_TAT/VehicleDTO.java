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
    private Boolean vehicleRequired;
    private List<String> typeOfVehicles;
    private Long vehicleVolume;
    private Boolean vehicleForAllProducts;
    private Boolean vehicleForAllPinncodes;
    private Boolean priorApprovalForVehicle;
    private Long durationOfActivity;
}
