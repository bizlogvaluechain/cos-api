package com.bizlog.rms.entities.escalationMatrix;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
@Table(name = "Bizlog_Tech_escalation_tbl")
public class BizlogTechEscalation extends EscalationMatrix {

}
