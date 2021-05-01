package com.shopKpr.controller.user;

import com.shopKpr.entity.notification.Notifications;
import com.shopKpr.service.notification.NotificationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class NotificationsControllerUser {
    private final NotificationService notificationService;

    public NotificationsControllerUser( NotificationService notificationService ) {
        this.notificationService = notificationService;
    }

    @GetMapping("/getAllNotifications")
    public List<Notifications> getAllNotifications()
    {
        return notificationService.getAllNotifications();
    }
}
