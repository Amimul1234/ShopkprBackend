package com.shopKpr.controller.admin;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.shopKpr.ModelClass.pushNotification.NotificationData;
import com.shopKpr.service.pushNotification.PushNotificationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("shopKpr/admin")
public class PushNotificationControllerAdmin
{
    private final PushNotificationService pushNotificationService;

    public PushNotificationControllerAdmin( PushNotificationService pushNotificationService )
    {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/send-notification")
    public String sendNotification(@RequestBody NotificationData notificationData,
                                   @RequestParam("topic") String topic) throws FirebaseMessagingException {

        return pushNotificationService.sendNotification(notificationData, topic);
    }
}
