package com.bizlog.rms.dto.locationService;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO extends BaseDTO {
    private List<String> countries;
    private List<String> states;
    private List<String> cities;
    private List<String> areas;
    private List<String> pinCodes;
    private String transportLinehaul;
    private List<String> vehicle;
}
