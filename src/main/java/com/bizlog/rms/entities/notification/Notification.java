package com.bizlog.rms.entities.notification;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "notification_tbl")
public class Notification extends BaseClientEntity {
    @Column(name = "isSmsRequired", nullable = false)
    private Boolean isSmsRequired;
    @Column(name = "isEmailRequired", nullable = false)
    private Boolean isEmailRequired;
    @Column(name = "isTicketScansRequired", nullable = false)
    private Boolean isTicketScansRequired;
    @Column(name = "isReportAlertsRequired", nullable = false)
    private Boolean isReportAlertsRequired;
    @Column(name = "isAlertNeededForNegativeCases", nullable = false)
    private Boolean isAlertNeededForNegativeCases;
}
