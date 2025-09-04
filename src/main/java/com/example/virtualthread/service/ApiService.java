package com.example.virtualthread.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com/posts/1";

    public String callExternalApi() {
        System.out.println("외부 API 호출 시작: " + Thread.currentThread());
        try {
            String response = restTemplate.getForObject(EXTERNAL_API_URL, String.class);
            System.out.println("외부 API 호출 완료: " + Thread.currentThread());
            return response;
        } catch (Exception e) {
            System.err.println("API 호출 중 오류 발생: " + e.getMessage());
            return "API 호출 실패";
        }
    }
}
