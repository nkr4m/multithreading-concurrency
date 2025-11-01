package com.nkr4m.multithreading;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nkr4m.multithreading.producerconsumer.ProducerConsumer;

@SpringBootApplication
public class MultithreadingConcurrencyApplication implements CommandLineRunner {

    private final ProducerConsumer producerConsumer;

    public MultithreadingConcurrencyApplication(ProducerConsumer producerConsumer) {
        this.producerConsumer = producerConsumer;
    }

    public static void main(String[] args) {
        SpringApplication.run(MultithreadingConcurrencyApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        Thread producerThread = new Thread(() -> {
//            try {
//                producerConsumer.produce();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }, "ProducerThread");
//
//        Thread consumerThread = new Thread(() -> {
//            try {
//                producerConsumer.consume();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }, "ConsumerThread");
//
//        producerThread.start();
//        consumerThread.start();
    	
    	
    }
}
