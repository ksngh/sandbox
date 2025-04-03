package com.practice.paymentassignment.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SystemMetricsLogger {

    @Scheduled(fixedRate = 10000)
    public void logMetrics() {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory() / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        int availableProcessors = runtime.availableProcessors();

        log.info("System Metrics - FreeMemory: {} MB, TotalMemory: {} MB, CPU: {} cores",
                freeMemory, totalMemory, availableProcessors);
    }
}