package com.bizlog.rms.dto.locationService;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.entities.location.Charge;
import com.bizlog.rms.entities.location.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO extends BaseDTO {
    private String bizlogLocationMaster;
    private ServiceType serviceType;
    private Charge charge;
    private String selectStates;
    private String selectCities;
}
