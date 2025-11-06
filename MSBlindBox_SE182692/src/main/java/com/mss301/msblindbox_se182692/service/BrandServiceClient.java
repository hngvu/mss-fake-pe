package com.mss301.msblindbox_se182692.service;

import com.mss301.msblindbox_se182692.dto.BlindBoxRequest;
import com.mss301.msblindbox_se182692.entity.Brand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "brand-service", url = "${app.service.brand.url}")
public interface BrandServiceClient {
    @GetMapping("/brands/{id}")
    Brand getById(@PathVariable Integer id);

    @PostMapping("/blindboxes")
    void syncAdd(@RequestBody BlindBoxRequest blindBoxRequest);

    @PutMapping("/blindboxes/{id}")
    void syncUpdate(@PathVariable int id, @RequestBody BlindBoxRequest blindBoxRequest);

    @DeleteMapping("/blindboxes/{id}")
    void syncDelete(@PathVariable int id);
}