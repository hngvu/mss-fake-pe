package com.mss301.msblindbox_se182692.controller;

import com.mss301.msblindbox_se182692.entity.BlindBox;
import com.mss301.msblindbox_se182692.entity.Category;
import com.mss301.msblindbox_se182692.service.BlindBoxService;
import com.mss301.msblindbox_se182692.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blindboxes")
public class BlindBoxController {
    private final BlindBoxService blindBoxService;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<BlindBox>> getAllBlindBoxes() {
        return ResponseEntity.ok(blindBoxService.getAllBlindBoxes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlindBox> getBlindBoxById(@PathVariable Integer id) {
        return ResponseEntity.ok(blindBoxService.getBlindBoxById(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }
}
