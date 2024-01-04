package com.bizlog.rms.entities.escalationMatrix;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Bizlog_Ops_escalation_tbl")
public class BizlogOpsEscalation extends EscalationMatrix {

}
