package com.nkr4m.multithreading.producerconsumer;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification() {
        System.out.println("Notification sent by thread " + Thread.currentThread().getName());
    }
}

