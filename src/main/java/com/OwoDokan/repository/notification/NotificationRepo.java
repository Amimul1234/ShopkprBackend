package com.OwoDokan.repository.notification;

import com.OwoDokan.entity.notification.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notifications, Long> {
}
