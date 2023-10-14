package com.bizlog.rms.dto.SOP_TAT.subLists;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class MajorActivites {
    public List<String> sPS;
    public List<String> multiProductShipment;
    public List<String> multiPiceShipment;
    public List<String> linehaul;
    public List<String> fullTruckLoad;
    public List<String> lessThanTruckLoad;
}
