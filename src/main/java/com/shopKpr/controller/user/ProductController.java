package com.shopKpr.controller.user;

import com.shopKpr.entity.product.OwoProduct;
import com.shopKpr.service.product.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController( ProductService productService ) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getProductByBrand")
    public List<OwoProduct> getProductViaBrand( @RequestParam(name = "page") int page,
                                                @RequestParam("brandsId") Long brandsId)
    {
        return productService.getProductsByBrand(page, brandsId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getSimilarProducts")
    public List<OwoProduct> getSimilarProducts(@RequestParam("product_sub_category_id") Long product_sub_category_id)
    {
        return productService.getSimilarProducts(product_sub_category_id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/searchProduct")
    public List<OwoProduct> searchProduct(@RequestParam("page") int page, @RequestParam("subCategories") List<String> subCategories,
                                          @RequestParam("product_name") String product_name)
    {
        return productService.searchProductViaSubCategories(page, subCategories, product_name);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/searchProductDesc")
    public List<OwoProduct> searchProductDesc(@RequestParam("page") int page, @RequestParam("subCategories") List<String> subCategories,
                                              @RequestParam("product_name") String product_name)
    {
        return productService.searchProductViaSubCategoriesDesc(page, subCategories, product_name);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/sortProductAlphabetically")
    public List<OwoProduct> sortProductAlphabetically(@RequestParam("page") int page, @RequestParam("subCategories") List<String> subCategories,
                                                      @RequestParam("alphabet") String alphabet)
    {
        return productService.sortAlphabetically(page, subCategories, alphabet);
    }
}
