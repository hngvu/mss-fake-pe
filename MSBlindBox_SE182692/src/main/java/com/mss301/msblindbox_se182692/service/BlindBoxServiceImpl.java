package com.mss301.msblindbox_se182692.service;

import com.mss301.msblindbox_se182692.entity.BlindBox;
import com.mss301.msblindbox_se182692.exception.ResourceNotFoundException;
import com.mss301.msblindbox_se182692.repo.BlindBoxRepo;
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
    public BlindBox addBlindBox(BlindBox blindBox) {
        blindBox.setReleaseDate(LocalDate.now());
        if (!brandServiceClient.check(blindBox.getBrandId())) {
            throw new ResourceNotFoundException("Brand " + blindBox.getBrandId() + " not found");
        }
        return blindBoxRepo.save(blindBox);
    }

    @Override
    public BlindBox updateBlindBox(BlindBox blindBox) {
        
        return blindBoxRepo.save(blindBox);
    }

    @Override
    public void deleteBlindBox(Integer id) {
        blindBoxRepo.deleteById(id);
    }
}
