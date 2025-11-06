package com.mss301.msblindbox_se182692.service;

import com.mss301.msblindbox_se182692.dto.BlindBoxRequest;
import com.mss301.msblindbox_se182692.entity.BlindBox;
import com.mss301.msblindbox_se182692.exception.ResourceNotFoundException;
import com.mss301.msblindbox_se182692.repo.BlindBoxRepo;
import com.mss301.msblindbox_se182692.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlindBoxServiceImpl implements BlindBoxService {
    private final BlindBoxRepo blindBoxRepo;
    private final BrandServiceClient brandServiceClient;
    private final CategoryRepo categoryRepo;

    @Override
    public List<BlindBox> getAllBlindBoxes() {
        return blindBoxRepo.findAll(
                Sort.by(Sort.Order.desc("id")));
    }

    @Override
    public BlindBox getBlindBoxById(Integer id) {
        return blindBoxRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blindbox " + id + " not found"));
    }

    @Override
    public void addBlindBox(BlindBoxRequest blindBoxRequest) {

            var brand = brandServiceClient.getById(blindBoxRequest.brandId());

            if (brand == null) {
                throw new ResourceNotFoundException("Brand " + blindBoxRequest.brandId() + " not found");
            }

            var category = categoryRepo.getReferenceById(blindBoxRequest.categoryId());

            if (category == null) {
                throw new ResourceNotFoundException("Category " + blindBoxRequest.categoryId() + " not found");
            }

             blindBoxRepo.save(BlindBox.builder()
                     .name(blindBoxRequest.name())
                     .rarity(blindBoxRequest.rarity())
                     .price(blindBoxRequest.price())
                     .category(category)
                     .stock(blindBoxRequest.stock())
                     .releaseDate(LocalDate.now())
                     .brandId(blindBoxRequest.brandId())
                     .build());

            brandServiceClient.syncAdd(blindBoxRequest);
    }

    @Override
    public void updateBlindBox(int id, BlindBoxRequest blindBoxRequest) {
        var blindBox = blindBoxRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blindbox " + id + " not found"));

        var brand = brandServiceClient.getById(blindBoxRequest.brandId());

        if (brand == null) {
            throw new ResourceNotFoundException("Brand " + blindBoxRequest.brandId() + " not found");
        }

        var category = categoryRepo.getReferenceById(blindBoxRequest.categoryId());

        if (category == null) {
            throw new ResourceNotFoundException("Category " + blindBoxRequest.categoryId() + " not found");
        }

        brandServiceClient.syncUpdate(id, blindBoxRequest);

        blindBoxRepo.save(
                BlindBox.builder()
                        .id(id)
                        .name(blindBoxRequest.name())
                        .rarity(blindBoxRequest.rarity())
                        .price(blindBoxRequest.price())
                        .category(category)
                        .stock(blindBoxRequest.stock())
                        .releaseDate(blindBox.getReleaseDate())
                        .brandId(blindBoxRequest.brandId())
                        .build()
        );
    }

    @Override
    public void deleteBlindBox(Integer id) {
        blindBoxRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blindbox " + id + " not found"));
        brandServiceClient.syncDelete(id);
        blindBoxRepo.deleteById(id);
    }
}
