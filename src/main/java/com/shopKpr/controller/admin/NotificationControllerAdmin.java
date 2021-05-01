package com.shopKpr.controller.admin;

import com.shopKpr.entity.notification.Notifications;
import com.shopKpr.service.notification.NotificationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("shopKpr/admin")
public class NotificationControllerAdmin {

    private final NotificationService notificationService;

    public NotificationControllerAdmin( NotificationService notificationService ) {
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
