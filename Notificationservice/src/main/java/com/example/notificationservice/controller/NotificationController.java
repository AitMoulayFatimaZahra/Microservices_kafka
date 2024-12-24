package com.example.notificationservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @GetMapping("/api/status")
    public String status() {
        return "Microservice Notifications opérationnel";
    }
}

