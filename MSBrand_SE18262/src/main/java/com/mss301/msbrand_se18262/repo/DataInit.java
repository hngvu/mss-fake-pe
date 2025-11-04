package com.mss301.msbrand_se18262.repo;

import com.mss301.msbrand_se18262.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final BrandRepo brandRepo;

    @Override
    public void run(String... args) throws Exception {
        if (brandRepo.count() == 0) {
            brandRepo.save(
                    Brand.builder()
                            .id(1)
                            .name("POP MART")
                            .countryOfOrigin("China")
                            .build()
            );

            brandRepo.save(
                    Brand.builder()
                            .id(2)
                            .name("Funko")
                            .countryOfOrigin("USA")
                            .build()
            );
        }
    }
}
