package com.mss301.msblindbox_se182692.service;

import com.mss301.msblindbox_se182692.entity.BlindBox;
import com.mss301.msblindbox_se182692.repo.BlindBoxRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlindBoxServiceImpl implements BlindBoxService {
    private final BlindBoxRepo blindBoxRepo;

    @Override
    public List<BlindBox> getAllBlindBoxes() {
        return blindBoxRepo.findAll();
    }

    @Override
    public BlindBox getBlindBoxById(Integer id) {
        return blindBoxRepo.findById(id).orElse(null);
    }

    @Override
    public void addBlindBox(BlindBox blindBox) {

    }

    @Override
    public void updateBlindBox(BlindBox blindBox) {

    }

    @Override
    public void deleteBlindBox(Integer id) {

    }
}
