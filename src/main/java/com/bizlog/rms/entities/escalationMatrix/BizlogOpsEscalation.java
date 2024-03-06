package com.bizlog.rms.entities.escalationMatrix;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
@Table(name = "Bizlog_Ops_escalation_tbl")
public class BizlogOpsEscalation extends EscalationMatrix {

}
