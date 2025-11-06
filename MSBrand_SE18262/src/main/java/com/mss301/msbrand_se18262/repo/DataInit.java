package com.mss301.msbrand_se18262.repo;

import com.mss301.msbrand_se18262.entity.BlindBox;
import com.mss301.msbrand_se18262.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final BrandRepo brandRepo;
    private final BlindBoxRepo blindBoxRepo;

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

            if  (blindBoxRepo.count() == 0) {
                Brand brand = brandRepo.findById(1).orElse(null);

                blindBoxRepo.save(
                        BlindBox.builder()
                                .name("Dragon Warrior")
                                .categoryId(1)
                                .brand(brand)
                                .rarity("Rare")
                                .price(29.99)
                                .releaseDate(LocalDate.parse("01-11-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                                .stock(90)
                                .build()
                );
            }
        }
    }
}
