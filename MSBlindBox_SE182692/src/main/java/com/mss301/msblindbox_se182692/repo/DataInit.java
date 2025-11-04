package com.mss301.msblindbox_se182692.repo;

import com.mss301.msblindbox_se182692.entity.BlindBox;
import com.mss301.msblindbox_se182692.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final BlindBoxRepo blindboxRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepo.count() == 0) {
            categoryRepo.save(
                    Category.builder()
                            .id(1)
                            .name("Fantasy")
                            .description("Mystical creatures, wizards, and legendary beings")
                            .rarityLevel("Common to Ultra Rare")
                            .priceRange("$10 - $60")
                            .build()
            );

            categoryRepo.save(
                    Category.builder()
                            .id(2)
                            .name("Anime")
                            .description("")
                            .rarityLevel("Common to Legendary")
                            .priceRange("$15 - $160")
                            .build()
            );
        }

        if  (blindboxRepo.count() == 0) {
            Category category = categoryRepo.findById(1).orElse(null);

            blindboxRepo.save(
                    BlindBox.builder()
                            .name("Dragon Warrior")
                            .category(category)
                            .brandId(1)
                            .rarity("Rare")
                            .price(29.99)
                            .releaseDate(LocalDate.parse("01-11-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                            .stock(90)
                            .build()
            );
        }
    }
}
