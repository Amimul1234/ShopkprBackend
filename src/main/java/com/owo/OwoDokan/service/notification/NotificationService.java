package com.owo.OwoDokan.service.notification;

import com.owo.OwoDokan.entity.notification.Notifications;
import com.owo.OwoDokan.repository.notification.NotificationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepo notificationRepo;

    public NotificationService( NotificationRepo notificationRepo ) {
        this.notificationRepo = notificationRepo;
    }


    public void createNewNotice( Notifications notifications ) {
        notificationRepo.save(notifications);
    }

    public void updateNotification( Notifications notifications ) {
        Optional<Notifications> notificationsOptional = notificationRepo.findById(notifications.getNotificationId());


        if(notificationsOptional.isPresent())
        {
            Notifications notifications1 = notificationsOptional.get();

            notifications1.setNotificationName(notifications.getNotificationName());
            notifications1.setNotificationImage(notifications.getNotificationImage());

            notificationRepo.save(notifications1);
        }
        else {
            throw new RuntimeException("Notification not found");
        }
    }

    public void deleteNotification( Long notificationId ) {
        notificationRepo.deleteById(notificationId);
    }

    public List<Notifications> getAllNotifications() {
        return notificationRepo.findAll();
    }
}
