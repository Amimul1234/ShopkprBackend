package com.owo.OwoDokan.controller.adminControls.categoryManagement;

import com.owo.OwoDokan.entity.category.CategoryEntity;
import com.owo.OwoDokan.service.category.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CategoryManagement {
    private final CategoryService categoryService;

    public CategoryManagement( CategoryService categoryService ) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addNewCategory")
    public String addNewCategory(@RequestBody CategoryEntity categoryEntity)
    {
        return categoryService.addNewCategory(categoryEntity);
    }

    @GetMapping("/getAllCategories")
    public List<CategoryEntity> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @GetMapping("/getCategoryBasedOnId")
    public CategoryEntity getCategoryBasedOnId(@RequestParam(name = "categoryId") Long categoryId)
    {
        return categoryService.getCategoryByID(categoryId);
    }

    @GetMapping("/getCategoryListBasedOnId")
    public List<String> getCategoryListBasedOnId(@RequestParam(name = "categoryIds") List<Long> categoryIds)
    {
        return categoryService.getCategoriesByIds(categoryIds);
    }

    @PutMapping("/updateCategory")
    public String updateCategory(@RequestParam(name = "categoryId") Long categoryId, @RequestBody CategoryEntity categoryEntity)
    {
        return categoryService.updateCategory(categoryId, categoryEntity);
    }

    @DeleteMapping("/deleteCategory")
    public String deleteCategory(@RequestParam(name = "categoryId") Long categoryId)
    {
        return categoryService.deleteCategory(categoryId);
    }
}
