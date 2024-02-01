package com.bizlog.rms.dto.product;

import com.bizlog.rms.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class ProductVehicleDTO extends BaseDTO {
    @JsonProperty("Product_Info_Id")
    private Long productInfoId;
    private Boolean isBizlogVehicleNeededLinehaul;
    private List<String> vehicleType;
    private Boolean isBizlogWarehouseNeeded;
    private String country;
    private String state;
    private String city;
    private String warehouse;
    private Boolean is3PLWarehouseRequired;
}
