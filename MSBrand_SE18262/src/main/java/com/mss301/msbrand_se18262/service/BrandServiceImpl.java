package com.mss301.msbrand_se18262.service;

import com.mss301.msbrand_se18262.entity.Brand;
import com.mss301.msbrand_se18262.repo.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepo brandRepo;
    @Override
    public List<Brand> getAllBrands() {
        return brandRepo.findAll();
    }
}
