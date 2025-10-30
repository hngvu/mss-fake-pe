package com.mss301.msblindbox_se182692.service;

import com.mss301.msblindbox_se182692.entity.Category;
import com.mss301.msblindbox_se182692.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements  CategoryService {
    private final CategoryRepo categoryRepo;
    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }
}
