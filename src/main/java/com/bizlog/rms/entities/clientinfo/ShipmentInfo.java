package com.bizlog.rms.entities.clientinfo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "client_shipment_info_table")
public class ShipmentInfo extends BaseAddress {

}
