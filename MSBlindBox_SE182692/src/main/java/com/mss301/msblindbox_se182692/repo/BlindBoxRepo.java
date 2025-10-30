package com.mss301.msblindbox_se182692.repo;

import com.mss301.msblindbox_se182692.entity.BlindBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlindBoxRepo extends JpaRepository<BlindBox, Integer> {
}
