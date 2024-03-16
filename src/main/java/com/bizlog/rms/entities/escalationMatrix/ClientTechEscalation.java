package com.bizlog.rms.entities.escalationMatrix;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
@Table(name = "client_Tech_escalation_tbl")
public class ClientTechEscalation extends EscalationMatrix {
}
