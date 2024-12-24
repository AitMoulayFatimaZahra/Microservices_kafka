package com.example.notificationservice.service;


import com.example.notificationservice.model.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification enregistrerNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void envoyerNotification(Notification notification) {
        // Simuler l'envoi d'une notification (email, SMS, etc.)
        System.out.println("Notification envoyée : " + notification.getMessage());
    }
}

