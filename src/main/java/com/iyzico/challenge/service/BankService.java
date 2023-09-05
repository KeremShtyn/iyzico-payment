package com.iyzico.challenge.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    /**
     * Bank Latency Simulation (avg: 5 seconds)
     */

    private final ThreadPoolTaskExecutor taskExecutor;

    public BankService() {
        taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(30);
        taskExecutor.initialize();
    }
    public BankPaymentResponse pay(BankPaymentRequest request) {
        try {
            Thread.sleep(5000);
            return new BankPaymentResponse("200");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
