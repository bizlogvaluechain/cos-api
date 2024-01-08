package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO extends BaseDTO {
    private String typeOfVehicle;
    private String noOfVehicle;
    private Boolean isVehicleRequiredAllProduct;
    private Boolean isVehicleRequiredAllPincodes;
    private Boolean isPriorApprovalNeededBeforeUsingVehicle;
}
