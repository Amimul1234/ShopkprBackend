package com.owo.OwoDokan.repository.category;

import com.owo.OwoDokan.entity.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
}
