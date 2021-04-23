package com.owo.OwoDokan.controller.adminControls.productManagement;

import com.owo.OwoDokan.entity.product.OwoProduct;
import com.owo.OwoDokan.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductManagement
{
    private final ProductService productService;

    public ProductManagement( ProductService productService ) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public List<OwoProduct> getAllProducts( @RequestParam(name = "page") int page)
    {
        return productService.getAllProducts(page);
    }

    @GetMapping("/getAllStockedOutProducts")
    public List<OwoProduct> getAllStockedOutProducts(@RequestParam("page") int page)
    {
        return productService.getAllStockedOutProducts(page);
    }

    @GetMapping("/getAProduct")
    public OwoProduct getAProduct(@RequestParam(name = "productId") Long productId)
    {
        return productService.getAProduct(productId);
    }

    @GetMapping("/getProductByBrand")
    public List<OwoProduct> getProductViaBrand(@RequestParam(name = "page") int page, @RequestParam("brandsId") Long brandsId)
    {
        return productService.getProductsByBrand(page, brandsId);
    }

    @GetMapping("/getSimilarProducts")
    public List<OwoProduct> getSimilarProducts(@RequestParam("product_sub_category_id") Long product_sub_category_id)
    {
        return productService.getSimilarProducts(product_sub_category_id);
    }

    @PostMapping("/addProduct") //This method is for adding new products
    public OwoProduct addProduct(@RequestBody OwoProduct product)
    {
        return productService.saveProduct(product);
    }

    @PutMapping("/updateProduct")
    public OwoProduct updateProductInformation(@RequestBody OwoProduct product)
    {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam("productId") Long productId)
    {
        productService.deleteProduct(productId);
    }

    @GetMapping("/searchProductWithName")
    public List<OwoProduct> searchProduct_admin(@RequestParam(name = "page") int page,
                                                @RequestParam(name = "product_name") String product_name)
    {
        return productService.searchProductAdmin(page, product_name);
    }

    @GetMapping("/searchProduct")
    public List<OwoProduct> searchProduct(@RequestParam("page") int page, @RequestParam("subCategories") List<String> subCategories,
                                          @RequestParam("product_name") String product_name)
    {
        return productService.searchProductViaSubCategories(page, subCategories, product_name);
    }


    @GetMapping("/searchProductDesc")
    public List<OwoProduct> searchProductDesc(@RequestParam("page") int page, @RequestParam("subCategories") List<String> subCategories,
                                          @RequestParam("product_name") String product_name)
    {
        return productService.searchProductViaSubCategoriesDesc(page, subCategories, product_name);
    }

    @GetMapping("/sortProductAlphabetically")
    public List<OwoProduct> sortProductAlphabetically(@RequestParam("page") int page, @RequestParam("subCategories") List<String> subCategories,
                                                      @RequestParam("alphabet") String alphabet)
    {
        return productService.sortAlphabetically(page, subCategories, alphabet);
    }

}
