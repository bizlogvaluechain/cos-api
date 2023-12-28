package com.bizlog.rms.entities.clientinfo;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client_hierarchy_tbl")
public class ClientHierarchy extends BaseClientEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "designation", nullable = false)
    private String designation;
    @Column(name = "emailId", nullable = false)
    private String emailId;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
}
