package com.example.virtualthread.controller;

import com.example.virtualthread.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
public class VirtualThreadController {

    private final ExecutorService virtualThreadExecutor;
    private final ApiService apiService;

    @GetMapping("/api/call-external")
    public String callExternalApiWithVirtualThread() {
        long startTime = System.currentTimeMillis();
        System.out.println("요청 처리 시작 (스레드: " + Thread.currentThread() + ")");

        Future<String> future = virtualThreadExecutor.submit(() -> {
            // 가상 스레드에서 I/O 블로킹 작업 수행
            return apiService.callExternalApi();
        });

        try {
            String result = future.get(); // 가상 스레드 작업 완료를 기다림
            long endTime = System.currentTimeMillis();
            System.out.println("요청 처리 완료. 소요 시간: " + (endTime - startTime) + "ms");
            return "API 호출 결과: " + result;
        } catch (Exception e) {
            return "작업 실패: " + e.getMessage();
        }
    }
}
