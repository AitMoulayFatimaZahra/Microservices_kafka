package com.example.notificationservice.kafka;

import com.example.notificationservice.model.Notification;
import com.example.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final NotificationService notificationService;

    @KafkaListener(topics = "commandes_topic", groupId = "notifications_group")
    public void consommerMessage(Notification notification) {
        // Enregistrer la notification dans la base de données
        notificationService.enregistrerNotification(notification);

        // Envoyer la notification
        notificationService.envoyerNotification(notification);
    }
}
