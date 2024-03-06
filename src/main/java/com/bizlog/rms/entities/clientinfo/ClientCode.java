package com.bizlog.rms.entities.clientinfo;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
@Table(name = "client_code_table")
public class ClientCode extends BaseClientEntity {

    @Column(name = "clientCode", nullable = false)
    private String clientCode;
}
