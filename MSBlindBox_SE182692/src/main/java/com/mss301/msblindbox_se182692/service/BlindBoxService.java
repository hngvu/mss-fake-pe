package com.mss301.msblindbox_se182692.service;

import com.mss301.msblindbox_se182692.entity.BlindBox;

import java.util.List;

public interface BlindBoxService {
    List<BlindBox> getAllBlindBoxes();
    BlindBox getBlindBoxById(Integer id);
    BlindBox addBlindBox(BlindBox blindBox);
    BlindBox updateBlindBox(BlindBox blindBox);
    void deleteBlindBox(Integer id);
}
