package com.bizlog.rms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
@Table(name = "client_setting", uniqueConstraints = @UniqueConstraint(columnNames = { "id", "client_id" }))
public class ClientSetting extends BaseClientEntity {

    @Column(name = "ticketLimitPerDay", nullable = false)
    private int ticketLimitPerDay;

}
