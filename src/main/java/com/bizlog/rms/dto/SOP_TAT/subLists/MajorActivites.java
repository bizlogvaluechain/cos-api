package com.bizlog.rms.dto.SOP_TAT.subLists;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class MajorActivites {
    public String sPS;
    public String multiProductShipment;
    public String multiPiceShipment;
    public String linehaul;
    public String fullTruckLoad;
    public String lessThanTruckLoad;
}
