package com.shopKpr.controller.pushNotification;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.shopKpr.ModelClass.pushNotification.NotificationData;
import com.shopKpr.service.pushNotification.PushNotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class pushNotificationController
{
    private final PushNotificationService pushNotificationService;

    public pushNotificationController( PushNotificationService pushNotificationService )
    {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/send-notification")
    public String sendNotification(@RequestBody NotificationData notificationData,
                                   @RequestParam("topic") String topic) throws FirebaseMessagingException {

        return pushNotificationService.sendNotification(notificationData, topic);
    }
}
