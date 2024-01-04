package com.bizlog.rms.entities.escalationMatrix;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Bizlog_Finance_escalation_tbl")
public class BizlogFinanceEscalation extends BaseClientEntity {
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "emailAddress", nullable = false)
    private String emailAddress;
    @Column(name = "mobile", nullable = false)
    private String mobile;
    @Column(name = "designation", nullable = false)
    private String designation;

}
