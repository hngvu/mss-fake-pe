package com.mss301.msbrand_se18262.controller;

import com.mss301.msbrand_se18262.entity.Brand;
import com.mss301.msbrand_se18262.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Integer id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }
}
