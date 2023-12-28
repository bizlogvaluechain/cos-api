package com.bizlog.rms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "client_info", uniqueConstraints = @UniqueConstraint(columnNames = { "id", "client_id" }))
public class CustomerInfo extends BaseClientEntity {
    @Column(name = "clientName", nullable = false)
    private String clientName;
    @Column(name = "legalEntityType", nullable = false)
    private String legalEntityType;
    @Column(name = "clientBrandName", nullable = false)
    private String clientBrandName;
    @Column(name = "clientSector", nullable = false)
    private String clientSector;
    @Column(name = "companySize", nullable = false)
    private Integer companySize;
    @Column(name = "registeredYear", nullable = false)
    private String registeredYear;
    @Column(name = "companyRevenue", nullable = false)
    private Long companyRevenue;
    @Column(name = "clientRevenueFY", nullable = false)
    private Long clientRevenueFY;
    @Column(name = "gst", nullable = false)
    private String gst;
    @Column(name = "panOrAadhar", nullable = false)
    private String panOrAadhar;
    @Column(name = "gstS3Key", nullable = false)
    private String gstS3Key;
    @Column(name = "panOrAadharS3Key", nullable = false)
    private String panOrAadharS3Key;
}
