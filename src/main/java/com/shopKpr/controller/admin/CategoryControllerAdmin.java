package com.shopKpr.controller.admin;

import com.shopKpr.entity.category.CategoryEntity;
import com.shopKpr.service.category.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("shopKpr/admin")
public class CategoryControllerAdmin
{
    private final CategoryService categoryService;

    public CategoryControllerAdmin( CategoryService categoryService ) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addNewCategory")
    public String addNewCategory(@RequestBody CategoryEntity categoryEntity)
    {
        return categoryService.addNewCategory(categoryEntity);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateCategory")
    public String updateCategory(@RequestParam(name = "categoryId") Long categoryId, @RequestBody CategoryEntity categoryEntity)
    {
        return categoryService.updateCategory(categoryId, categoryEntity);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteCategory")
    public String deleteCategory(@RequestParam(name = "categoryId") Long categoryId)
    {
        return categoryService.deleteCategory(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllCategories")
    public List<CategoryEntity> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getCategoryBasedOnId")
    public CategoryEntity getCategoryBasedOnId(@RequestParam(name = "categoryId") Long categoryId)
    {
        return categoryService.getCategoryByID(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getCategoryListBasedOnId")
    public List<String> getCategoryListBasedOnId(@RequestParam(name = "categoryIds") List<Long> categoryIds)
    {
        return categoryService.getCategoriesByIds(categoryIds);
    }
}
