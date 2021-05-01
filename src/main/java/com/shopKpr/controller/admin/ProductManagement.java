package com.shopKpr.controller.admin;

import com.shopKpr.entity.product.OwoProduct;
import com.shopKpr.service.product.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("shopKpr/admin")
public class ProductManagement
{
    private final ProductService productService;

    public ProductManagement( ProductService productService ) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllProducts")
    public List<OwoProduct> getAllProducts( @RequestParam(name = "page") int page)
    {
        return productService.getAllProducts(page);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllStockedOutProducts")
    public List<OwoProduct> getAllStockedOutProducts(@RequestParam("page") int page)
    {
        return productService.getAllStockedOutProducts(page);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAProduct")
    public OwoProduct getAProduct(@RequestParam(name = "productId") Long productId)
    {
        return productService.getAProduct(productId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addProduct")
    public OwoProduct addProduct(@RequestBody OwoProduct product)
    {
        return productService.saveProduct(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateProduct")
    public OwoProduct updateProductInformation(@RequestBody OwoProduct product)
    {
        return productService.updateProduct(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam("productId") Long productId)
    {
        productService.deleteProduct(productId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/searchProductWithName")
    public List<OwoProduct> searchProduct_admin(@RequestParam(name = "page") int page,
                                                @RequestParam(name = "product_name") String product_name)
    {
        return productService.searchProductAdmin(page, product_name);
    }
}
