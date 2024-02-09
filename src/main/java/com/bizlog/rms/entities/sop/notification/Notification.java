package com.bizlog.rms.entities.sop.notification;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "notification_tbl")
public class  Notification extends BaseClientEntity {
    @Column(name = "sms", nullable = false)
    private List<String> sms;

    @Column(name = "email", nullable = false)
    private List<String> email;


}
