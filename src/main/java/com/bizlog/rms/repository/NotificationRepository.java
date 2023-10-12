package com.bizlog.rms.repository;

import com.bizlog.rms.entities.notification.Notification;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends BaseClientRepository<Notification, Long> {
}
