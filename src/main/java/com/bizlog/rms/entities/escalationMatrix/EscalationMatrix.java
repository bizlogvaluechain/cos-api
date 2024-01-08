package com.bizlog.rms.entities.escalationMatrix;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
