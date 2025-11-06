package com.mss301.msbrand_se18262.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlindBoxRepo extends JpaRepository<com.mss301.msbrand_se18262.entity.BlindBox, Integer> {
}
