package com.bizlog.rms.entities.escalationMatrix;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "client_Tech_escalation_tbl")
public class ClientTechEscalation extends EscalationMatrix {
}
