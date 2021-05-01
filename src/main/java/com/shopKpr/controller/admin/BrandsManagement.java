package com.shopKpr.controller.admin;

import com.shopKpr.entity.brands.Brands;
import com.shopKpr.service.brand.BrandsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BrandsManagement
{
    private final BrandsService brandsService;

    public BrandsManagement( BrandsService brandsService ) {
        this.brandsService = brandsService;
    }

    @GetMapping("/getAllBrandsOfASubCategory")
    public List<Brands> getAllBrands( @RequestParam("subCategoryId") Long subCategoryId)
    {
        return brandsService.getAllBrands(subCategoryId);
    }

    @PostMapping("/addABrand")
    public void addBrand(@RequestBody Brands brands)
    {
        brandsService.createBrand(brands);
    }

    @PutMapping("/updateBrand")
    public String updateBrand(@RequestParam(name = "subCategoryId") Long subCategoryId, @RequestBody Brands brands)
    {
        return brandsService.updateBrand(subCategoryId, brands);
    }

    @DeleteMapping("/deleteBrand")
    public String deleteBrand(@RequestParam(name = "subCategoryId") Long subCategoryId,
                              @RequestParam(name = "brandsId") Long brandsId)
    {
        return brandsService.deleteBrand(subCategoryId, brandsId);
    }
}
