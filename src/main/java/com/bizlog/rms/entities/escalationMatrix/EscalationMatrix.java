package com.bizlog.rms.entities.escalationMatrix;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "escalation_matrix_tbl")
public class EscalationMatrix extends BaseClientEntity {
    @Column(name = "opsContactInfo", nullable = false)
    private String opsContactInfo;
    @Column(name = "itContactInfo", nullable = false)
    private String itContactInfo;
    @Column(name = "businessContactInfo", nullable = false)
    private String businessContactInfo;
    @Column(name = "accountContactInfo", nullable = false)
    private String accountContactInfo;
    @Column(name = "emergencyContactInfo", nullable = false)
    private String emergencyContactInfo;
}
