package com.mss301.msbrand_se18262.service;

import com.mss301.msbrand_se18262.dto.BlindBoxRequest;
import com.mss301.msbrand_se18262.entity.BlindBox;
import com.mss301.msbrand_se18262.exception.ResourceNotFoundException;
import com.mss301.msbrand_se18262.repo.BlindBoxRepo;
import com.mss301.msbrand_se18262.repo.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlindBoxServiceImpl implements com.mss301.msbrand_se18262.service.BlindBoxService {
    private final BlindBoxRepo blindBoxRepo;
    private final BrandRepo brandRepo;

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
        blindBoxRepo.save(BlindBox.builder()
                .name(blindBoxRequest.name())
                .rarity(blindBoxRequest.rarity())
                .price(blindBoxRequest.price())
                .categoryId(blindBoxRequest.categoryId())
                .stock(blindBoxRequest.stock())
                .releaseDate(LocalDate.now())
                .brand(brandRepo.getReferenceById(blindBoxRequest.brandId()))
                .build());
    }

    @Override
    public void updateBlindBox(int id, BlindBoxRequest blindBoxRequest) {
        var blindBox = blindBoxRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blindbox " + id + " not found"));

        blindBoxRepo.save(
                BlindBox.builder()
                        .id(id)
                        .name(blindBoxRequest.name())
                        .rarity(blindBoxRequest.rarity())
                        .price(blindBoxRequest.price())
                        .categoryId(blindBoxRequest.categoryId())
                        .stock(blindBoxRequest.stock())
                        .releaseDate(blindBox.getReleaseDate())
                        .brand(brandRepo.getReferenceById(blindBoxRequest.brandId()))
                        .build()
        );
    }

    @Override
    public void deleteBlindBox(Integer id) {
        blindBoxRepo.deleteById(id);
    }
}
