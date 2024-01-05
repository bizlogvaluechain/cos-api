package com.bizlog.rms.entities.sop.notification;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "notification_tbl")
public class Notification extends BaseClientEntity {
    @Column(name = "isSmsClient", nullable = false)
    private Boolean isSmsClient;
    @Column(name = "isEmailClient", nullable = false)
    private Boolean isEmailClient;
    @Column(name = "isSmsCustomer", nullable = false)
    private Boolean isSmsCustomer;
    @Column(name = "isEmailCustomer", nullable = false)
    private Boolean isEmailCustomer;

}
