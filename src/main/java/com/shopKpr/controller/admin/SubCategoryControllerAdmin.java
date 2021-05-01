package com.shopKpr.controller.admin;

import com.shopKpr.entity.category.SubCategoryEntity;
import com.shopKpr.service.category.SubCategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("shopKpr/admin")
public class SubCategoryControllerAdmin
{
    private final SubCategoryService subCategoryService;

    public SubCategoryControllerAdmin( SubCategoryService subCategoryService ) {
        this.subCategoryService = subCategoryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addNewSubCategory")
    public String addNewSubCategory( @RequestParam(name = "categoryId") Long categoryId,
                                     @RequestBody SubCategoryEntity subCategoryEntity)
    {
        return subCategoryService.addNewSubCategory(categoryId, subCategoryEntity);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllSubCategories")
    public List<SubCategoryEntity> getAllSubCategories( @RequestParam(name = "categoryId") Long categoryId)
    {
        return subCategoryService.getAllSubCategories(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateSubCategory")
    public String updateSubCategory(@RequestParam(name = "categoryId") Long categoryId, @RequestBody SubCategoryEntity subCategoryEntity)
    {
        return subCategoryService.updateSubCategory(categoryId, subCategoryEntity);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteSubCategory")
    public String deleteSubCategory(@RequestParam(name = "subCategoryId") Long subCategoryId)
    {
        return subCategoryService.deleteSubCategory(subCategoryId);
    }
}
