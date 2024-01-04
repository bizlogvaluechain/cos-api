package com.bizlog.rms.entities.escalationMatrix;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Bizlog_finance_escalation_tbl")
public class BizlogFinanceEscalation extends EscalationMatrix {
}
