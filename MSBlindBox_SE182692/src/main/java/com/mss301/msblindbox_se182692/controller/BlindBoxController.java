package com.mss301.msblindbox_se182692.controller;

import com.mss301.msblindbox_se182692.entity.BlindBox;
import com.mss301.msblindbox_se182692.entity.Category;
import com.mss301.msblindbox_se182692.service.BlindBoxService;
import com.mss301.msblindbox_se182692.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody BlindBox blindBox) {
        blindBoxService.addBlindBox(blindBox);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BlindBox> update(@Valid @RequestBody BlindBox blindBox) {
        return ResponseEntity.ok(blindBoxService.updateBlindBox(blindBox));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        blindBoxService.deleteBlindBox(id);
        return  ResponseEntity.ok().build();
    }


    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }
}
