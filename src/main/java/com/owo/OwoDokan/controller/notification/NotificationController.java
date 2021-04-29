package com.owo.OwoDokan.controller.notification;

import com.owo.OwoDokan.entity.notification.Notifications;
import com.owo.OwoDokan.service.notification.NotificationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController( NotificationService notificationService ) {
        this.notificationService = notificationService;
    }

    @PostMapping("/createNewNotification")
    public void createNewNotification( @RequestBody Notifications notifications)
    {
        notificationService.createNewNotice(notifications);
    }

    @PutMapping("/updateNotification")
    public void updateNotification(@RequestBody Notifications notifications)
    {
        notificationService.updateNotification(notifications);
    }

    @DeleteMapping("/deleteNotification")
    public void deleteNotification(@RequestParam("notificationId") Long notificationId)
    {
        notificationService.deleteNotification(notificationId);
    }

    @GetMapping("/getAllNotifications")
    public List<Notifications> getAllNotifications()
    {
        return notificationService.getAllNotifications();
    }
}
