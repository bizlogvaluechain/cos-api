package com.bizlog.rms.entities.escalationMatrix;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "client_Ops_escalation_tbl")
public class ClientOpsEscalation extends EscalationMatrix {
}
