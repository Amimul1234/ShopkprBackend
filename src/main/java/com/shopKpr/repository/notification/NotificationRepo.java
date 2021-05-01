package com.shopKpr.repository.notification;

import com.shopKpr.entity.notification.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notifications, Long> {
}
