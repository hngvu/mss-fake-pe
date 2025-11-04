package com.mss301.msblindbox_se182692.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BrandServiceClient {
    @Value("")
    private String url;
    private final RestTemplate restTemplate;

    public boolean check(int id) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url + "/brand-service/brands/" + id, Map.class);
            return response.getStatusCode().is2xxSuccessful() ? true : false;
        } catch (Exception e) {
            throw new RuntimeException("Brand service unavailable: " + e.getMessage());
        }
    }
}
