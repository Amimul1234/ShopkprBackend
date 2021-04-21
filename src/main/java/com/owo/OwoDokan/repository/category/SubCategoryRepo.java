package com.owo.OwoDokan.repository.category;

import com.owo.OwoDokan.entity.category.SubCategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepo extends JpaRepository<SubCategoryEntity, Long>
{
    @Query(value = "SELECT * FROM sub_category_entity s where s.sub_category_name = :subCategoryName",
            nativeQuery = true)
    Optional<SubCategoryEntity> findBySubCategoryName(@Param("subCategoryName") String subCategoryName);

    @Query("SELECT s FROM SubCategoryEntity s WHERE s.categoryEntity.categoryId IN (:categoryIds)")
    List<SubCategoryEntity> findByCategories( @Param("categoryIds") List<Long> categoryIds, Pageable pageable );
}
