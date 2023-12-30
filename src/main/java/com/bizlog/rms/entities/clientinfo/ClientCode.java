package com.bizlog.rms.entities.clientinfo;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client_code_table")
public class ClientCode extends BaseClientEntity {

    @Column(name = "clientCode", nullable = false)
    private String clientCodeNo;
}
