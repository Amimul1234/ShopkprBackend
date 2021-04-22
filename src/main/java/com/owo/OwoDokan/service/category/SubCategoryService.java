package com.owo.OwoDokan.service.category;

import com.owo.OwoDokan.entity.brands.Brands;
import com.owo.OwoDokan.entity.category.CategoryEntity;
import com.owo.OwoDokan.entity.category.SubCategoryEntity;
import com.owo.OwoDokan.exceptions.CategoryNotFoundException;
import com.owo.OwoDokan.exceptions.SubCategoryNotFound;
import com.owo.OwoDokan.repository.category.CategoryRepo;
import com.owo.OwoDokan.repository.category.SubCategoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubCategoryService {
    private final static Logger logger = LoggerFactory.getLogger(SubCategoryService.class);
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

    public SubCategoryService(SubCategoryRepo subCategoryRepo, CategoryRepo categoryRepo) {
        this.subCategoryRepo = subCategoryRepo;
        this.categoryRepo = categoryRepo;
    }

    public String addNewSubCategory(Long categoryId, SubCategoryEntity subCategoryEntity) {

        Optional<CategoryEntity> categoryEntity = categoryRepo.findById(categoryId);

        if(categoryEntity.isPresent())
        {
            CategoryEntity categoryEntity1 = categoryEntity.get();

            subCategoryEntity.setCategoryEntity(categoryEntity1);
            categoryEntity1.getSubCategoryEntities().add(subCategoryEntity);

            try
            {
                categoryRepo.save(categoryEntity1);
                return "Sub category added successfully";
            }
            catch (Exception e)
            {
                logger.error("Sub category service failed, Error is: "+e.getMessage());
                throw new RuntimeException(e);
            }

        }
        else
        {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    @Transactional
    public String updateSubCategory(Long categoryId, SubCategoryEntity subCategoryEntity)
    {

        Optional<CategoryEntity> categoryEntityOptional = categoryRepo.findById(categoryId);

        if(categoryEntityOptional.isPresent())
        {
            CategoryEntity categoryEntity = categoryEntityOptional.get();

            List<SubCategoryEntity> subCategoryEntityList = categoryEntity.getSubCategoryEntities();

            for(SubCategoryEntity subCategoryEntity1 : subCategoryEntityList)
            {
                if(subCategoryEntity.getSub_category_id().equals(subCategoryEntity1.getSub_category_id()))
                {
                    subCategoryEntity1.setSub_category_name(subCategoryEntity.getSub_category_name());
                    subCategoryEntity1.setSub_category_image(subCategoryEntity.getSub_category_image());
                    break;
                }
            }

            categoryEntity.setSubCategoryEntities(subCategoryEntityList);

            try
            {
                categoryRepo.save(categoryEntity);
                return "Sub category info updated successfully";
            }
            catch (Exception e)
            {
                logger.error("Sub category service error, Error is: "+e.getMessage());
                throw new RuntimeException(e);
            }
        }
        else
        {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    @Transactional
    public String deleteSubCategory(Long subCategoryId) {

        Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepo.findById(subCategoryId);

        if(subCategoryEntity.isPresent())
        {
            try
            {
                subCategoryRepo.delete(subCategoryEntity.get());
                return "Sub category deleted successfully";
            }
            catch (Exception e)
            {
                logger.error("Sub category service error, Error is: "+e.getMessage());
                throw new RuntimeException(e);
            }
        }
        else
        {
            throw new SubCategoryNotFound(subCategoryId);
        }
    }

    public List<SubCategoryEntity> getAllSubCategories(Long categoryId) {

        Optional<CategoryEntity> categoryEntity = categoryRepo.findById(categoryId);

        if(categoryEntity.isPresent())
        {
            CategoryEntity categoryEntity1 = categoryEntity.get();
            return categoryEntity1.getSubCategoryEntities();
        }
        else
        {
            log.error("Can not find category");
            throw new CategoryNotFoundException(categoryId);
        }
    }

    public List<String> getALlSubCategories( Long categoryId )
    {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepo.findById(categoryId);

        if(categoryEntityOptional.isPresent())
        {
            CategoryEntity categoryEntity = categoryEntityOptional.get();

            List<String> subCategoryNames = new ArrayList<>();

            categoryEntity.getSubCategoryEntities().forEach(v -> subCategoryNames.add(v.getSub_category_name()));

            return subCategoryNames;
        }
        else
        {
            log.error("Category with id: " + categoryId + " does not exists");
            throw new RuntimeException("Category with id: " + categoryId + " does not exists");
        }
    }

    public Long findSubCategoryId( String subCategoryName )
    {

        Optional<SubCategoryEntity> subCategoryEntityOptional = subCategoryRepo.findBySubCategoryName(subCategoryName);

        if(subCategoryEntityOptional.isPresent())
        {
            return subCategoryEntityOptional.get().getSub_category_id();
        }
        else
            throw new RuntimeException("Can not find sub category");
    }

    public List<SubCategoryEntity> getAllSubCategoriesPaging( List<Long> categoryIds, int page ) {
        int pageSize = 9; //products per page
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, pageSize);

        return subCategoryRepo.findByCategories(categoryIds, pageable);
    }

    public List<Brands> getAllBrandsViaSubCategory( Long subCategoryId ) {
        Optional<SubCategoryEntity> subCategoryEntityOptional = subCategoryRepo.findById(subCategoryId);

        if(subCategoryEntityOptional.isPresent())
        {
            SubCategoryEntity subCategoryEntity = subCategoryEntityOptional.get();
            return subCategoryEntity.getBrandsList();
        }
        else
        {
            log.warn("Can not find sub category with id: " + subCategoryId);
            throw new RuntimeException("Can not find sub category");
        }
    }
}
