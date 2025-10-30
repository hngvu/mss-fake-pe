package com.mss301.msblindbox_se182692.repo;

import com.mss301.msblindbox_se182692.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
