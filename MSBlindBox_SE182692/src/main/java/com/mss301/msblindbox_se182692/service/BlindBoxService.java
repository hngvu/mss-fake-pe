package com.mss301.msblindbox_se182692.service;

import com.mss301.msblindbox_se182692.dto.BlindBoxRequest;
import com.mss301.msblindbox_se182692.entity.BlindBox;

import java.util.List;

public interface BlindBoxService {
    List<BlindBox> getAllBlindBoxes();
    BlindBox getBlindBoxById(Integer id);
    void addBlindBox(BlindBoxRequest blindBox);
    void updateBlindBox(int id, BlindBoxRequest blindBox);
    void deleteBlindBox(Integer id);
}
