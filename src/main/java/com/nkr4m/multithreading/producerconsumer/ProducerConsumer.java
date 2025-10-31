package com.nkr4m.multithreading.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class ProducerConsumer {

    private final int Q_SIZE = 5;
    private final Queue<String> queue;
    private final NotificationService notificationService;

    public ProducerConsumer(NotificationService notificationService) {
        this.queue = new LinkedList<>();
        this.notificationService = notificationService;
    }

    // Producer Thread - Adds notifications to the queue
    public void produce() throws InterruptedException {
        int count = 0;
        while (true) {
            synchronized (this) {
                while (queue.size() == Q_SIZE) {
                    wait(); // Wait if queue is full
                }
                String notification = "Notification-" + count++;
                queue.add(notification);
                System.out.println("Produced: " + notification);
                notifyAll(); // Notify consumer threads
            }
            Thread.sleep(500); // Simulate time delay in producing
        }
    }

    // Consumer Thread - Removes notifications and processes them
    public void consume() throws InterruptedException {
        while (true) {
            String notification;
            synchronized (this) {
                while (queue.isEmpty()) {
                    wait(); // Wait if queue is empty
                }
                notification = queue.poll();
                notifyAll(); // Notify producer threads
            }
            notificationService.sendNotification();
            System.out.println("Consumed: " + notification);
            Thread.sleep(1000); // Simulate time delay in consuming
        }
    }
}

