package com.bizlog.rms.entities.escalationMatrix;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
@Table(name = "Bizlog_finance_escalation_tbl")
public class BizlogFinanceEscalation extends EscalationMatrix {
}
