package com.owo.OwoDokan.controller.adminControls.subCategoryManagement;

import com.owo.OwoDokan.entity.category.SubCategoryEntity;
import com.owo.OwoDokan.service.category.SubCategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class SubCategoryManagement
{
    private final SubCategoryService subCategoryService;

    public SubCategoryManagement( SubCategoryService subCategoryService ) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/getAllSubCategoriesForCategory")
    public List<String> getAllSubCategoriesForCategory(@RequestParam("categoryId") Long categoryId)
    {
        return subCategoryService.getALlSubCategories(categoryId);
    }

    @PostMapping("/addNewSubCategory")
    public String addNewSubCategory( @RequestParam(name = "categoryId") Long categoryId, @RequestBody SubCategoryEntity subCategoryEntity)
    {
        return subCategoryService.addNewSubCategory(categoryId, subCategoryEntity);
    }

    @GetMapping("/getAllSubCategories")
    public List<SubCategoryEntity> getAllSubCategories( @RequestParam(name = "categoryId") Long categoryId)
    {
        return subCategoryService.getAllSubCategories(categoryId);
    }

    @PutMapping("/updateSubCategory")
    public String updateSubCategory(@RequestParam(name = "categoryId") Long categoryId, @RequestBody SubCategoryEntity subCategoryEntity)
    {
        return subCategoryService.updateSubCategory(categoryId, subCategoryEntity);
    }

    @DeleteMapping("/deleteSubCategory")
    public String deleteSubCategory(@RequestParam(name = "subCategoryId") Long subCategoryId)
    {
        return subCategoryService.deleteSubCategory(subCategoryId);
    }
}
