package com.shopKpr.controller.user;

import com.shopKpr.entity.category.SubCategoryEntity;
import com.shopKpr.service.category.SubCategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SubCategoryControllerUser {
    private final SubCategoryService subCategoryService;

    public SubCategoryControllerUser( SubCategoryService subCategoryService ) {
        this.subCategoryService = subCategoryService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getAllSubCategoriesForCategory")
    public List<String> getAllSubCategoriesForCategory( @RequestParam("categoryId") Long categoryId)
    {
        return subCategoryService.getALlSubCategories(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getAllSubCategories")
    public List<SubCategoryEntity> getAllSubCategories( @RequestParam(name = "categoryId") Long categoryId)
    {
        return subCategoryService.getAllSubCategories(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getSubCategoriesPaging")
    public List<SubCategoryEntity> getSubCategoriesPaging(@RequestParam("page") int page, @RequestParam("categoryIds") List<Long> categoryIds)
    {
        return subCategoryService.getAllSubCategoriesPaging(categoryIds, page - 1);
    }

}
