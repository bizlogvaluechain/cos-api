package com.bizlog.rms.entities.clientinfo;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client_staging_table")
public class ClientStaging extends BaseClientEntity {
    @Column(name = "clientStage", nullable = false)
    private String clientStage;
}
