package com.bizlog.rms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "organization_setting", uniqueConstraints = @UniqueConstraint(columnNames = { "id", "org_id" }))
public class OrganizationSetting extends BaseOrganizationEntity {

    @Column(name = "ticketLimitPerDay", nullable = false)
    private int ticketLimitPerDay;


}
