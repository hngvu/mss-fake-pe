package com.mss301.msbrand_se18262.controller;

import com.mss301.msbrand_se18262.dto.BlindBoxRequest;
import com.mss301.msbrand_se18262.entity.BlindBox;
import com.mss301.msbrand_se18262.entity.Brand;
import com.mss301.msbrand_se18262.service.BlindBoxService;
import com.mss301.msbrand_se18262.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    private final BlindBoxService blindBoxService;

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Integer id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @GetMapping("/blindboxes")
    public ResponseEntity<List<BlindBox>> getAllBlindBoxes() {
        return ResponseEntity.ok(blindBoxService.getAllBlindBoxes());
    }

    @GetMapping("/blindboxes/{id}")
    public ResponseEntity<BlindBox> getBlindBoxById(@PathVariable Integer id) {
        return ResponseEntity.ok(blindBoxService.getBlindBoxById(id));
    }

    @PostMapping("/blindboxes")
    public ResponseEntity<Void> create(@Valid @RequestBody BlindBoxRequest blindBox) {
        blindBoxService.addBlindBox(blindBox);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/blindboxes/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @Valid @RequestBody BlindBoxRequest blindBox) {
        blindBoxService.updateBlindBox(id, blindBox);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/blindboxes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        blindBoxService.deleteBlindBox(id);
        return  ResponseEntity.ok().build();
    }
}
