package com.mss301.msbrand_se18262.service;

import com.mss301.msbrand_se18262.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    Brand getBrandById(int id);
}
