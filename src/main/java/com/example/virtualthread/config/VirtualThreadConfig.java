package com.example.virtualthread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class VirtualThreadConfig {
    @Bean
    public ExecutorService executorService() {
        // 가상 스레드 풀을 생성
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
