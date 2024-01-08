package com.bizlog.rms.repository;

import com.bizlog.rms.entities.sop.notification.Notification;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends BaseClientRepository<Notification, Long> {
}
