package com.bizlog.rms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NotifyApi_Tbl")
public class NotifyApi extends BaseClientEntity{
    @Column(name = "endPoint", nullable = false)
    private String endPoint;

    @Column(name = "authKey")
    private String authKey;

    @Column(name = "payload")
    private String payload;
}
