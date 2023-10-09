package com.bizlog.rms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "customer_info", uniqueConstraints = @UniqueConstraint(columnNames = { "id", "client_id" }))
public class CustomerInfo extends BaseClientEntity{
    @Column(name = "registeredEntity", nullable = false)
    private String registeredEntity;
    @Column(name = "entityType", nullable = false)
    private String entityType;
    @Column(name = "brandName", nullable = false)
    private String brandName;
    @Column(name = "industryVertical", nullable = false)
    private String industryVertical;
    @Column(name = "sector", nullable = false)
    private String sector;
    @Column(name = "gst", nullable = false)
    private String gst;
    @Column(name = "pan", nullable = false)
    private String pan;
    @Column(name = "msme", nullable = false)
    private String msme;
}
