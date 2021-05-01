package com.shopKpr.controller.user;

import com.shopKpr.entity.category.CategoryEntity;
import com.shopKpr.service.category.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CategoryControllerUser {
    private final CategoryService categoryService;

    public CategoryControllerUser( CategoryService categoryService ) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getAllCategories")
    public List<CategoryEntity> getAllCategories()
    {
        return categoryService.getAllCategories();
    }
}
