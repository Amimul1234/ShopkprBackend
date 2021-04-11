package com.owo.OwoDokan.service.pushNotification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.owo.OwoDokan.ModelClass.pushNotification.NotificationData;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {

    private final FirebaseMessaging firebaseMessaging;

    public PushNotificationService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public String sendNotification( NotificationData notificationData, String topic) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(notificationData.getSubject())
                .setBody(notificationData.getContent())
                .setImage(notificationData.getImageUrl())
                .build();

        Message message = Message
                .builder()
                .setTopic(topic)
                .setNotification(notification)
                .build();

        return firebaseMessaging.send(message);
    }
}
