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

    @Column(name = "escalationMatrixType", nullable = false)
    private String escalationMatrixType;
    @Column(name = "escalationType", nullable = false)
    private String escalationType;

}
