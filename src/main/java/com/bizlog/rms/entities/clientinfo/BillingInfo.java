package com.bizlog.rms.entities.clientinfo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "client_billing_info_table")
public class BillingInfo extends BaseAddress {

}
