package com.owo.OwoDokan.controller.shopKeeper;

import com.owo.OwoDokan.ModelClass.CartListFromClient;
import com.owo.OwoDokan.entity.brands.Brands;
import com.owo.OwoDokan.entity.product.OwoProduct;
import com.owo.OwoDokan.entity.shops.registration.ShopPendingRequest;
import com.owo.OwoDokan.entity.cart.CartListProduct;
import com.owo.OwoDokan.entity.category.CategoryEntity;
import com.owo.OwoDokan.entity.order.ShopKeeperOrders;
import com.owo.OwoDokan.service.brand.BrandsService;
import com.owo.OwoDokan.service.category.SubCategoryService;
import com.owo.OwoDokan.service.product.ProductService;
import com.owo.OwoDokan.service.cart.Shop_keeper_cart_service;
import com.owo.OwoDokan.service.category.CategoryService;
import com.owo.OwoDokan.service.order.ShopKeeperOrderService;
import com.owo.OwoDokan.service.shopKeeper.registration.RegistrationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class ShopKeeperRestController {

    private final ProductService productService;
    private final BrandsService brandsService;
    private final Shop_keeper_cart_service shop_keeper_cartService;
    private final ShopKeeperOrderService shop_keeper_orderService;
    private final RegistrationService registrationService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    public ShopKeeperRestController( ProductService productService, BrandsService brandsService, Shop_keeper_cart_service shop_keeper_cartService, ShopKeeperOrderService shop_keeper_orderService, RegistrationService registrationService, CategoryService categoryService, SubCategoryService subCategoryService ) {
        this.productService = productService;
        this.brandsService = brandsService;
        this.shop_keeper_cartService = shop_keeper_cartService;
        this.shop_keeper_orderService = shop_keeper_orderService;
        this.registrationService = registrationService;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    //Shop Registration Request
    @PostMapping("/shopRegisterRequest")
    public String shopRegisterRequest(@RequestBody ShopPendingRequest shopPendingRequest)
    {
        return registrationService.addNewRequest(shopPendingRequest);
    }

    @PostMapping("/shopKeeperCart")
    public String cartListItems(@RequestBody CartListFromClient cartListFromClient)
    {
        return shop_keeper_cartService.addCartItem(cartListFromClient);
    }

    @GetMapping("/shop_keeper_cart_products")
    public List<CartListProduct> getCartListForShopKeeper(@RequestParam(name = "mobile_number") String mobile_number) {
        return shop_keeper_cartService.getCartItems(mobile_number);
    }

    @PutMapping("/update_cart_list")
    public CartListProduct updateCartProduct(@RequestBody CartListProduct cart_list_product, @RequestParam(name = "mobile_number") String mobile_number)
    {
        return shop_keeper_cartService.updateCartItem(cart_list_product, mobile_number);
    }

    @DeleteMapping("/delete_cart_product")
    public void deleteCartProduct(@RequestParam(name = "product_id") int product_id, @RequestParam(name = "mobile_number") String mobile_number)
    {
        shop_keeper_cartService.deleteCartProduct(product_id, mobile_number);
    }

    @PostMapping("/shop_keeper_order")
    public void createOrder(@RequestBody ShopKeeperOrders shopKeeperOrders,
                                         @RequestParam(name = "mobile_number") String mobile_number)
    {
        shop_keeper_orderService.addOrder(shopKeeperOrders, mobile_number);
    }

    @GetMapping("/get_shop_keeper_order")
    public List<ShopKeeperOrders> getShopKeeperOrders( @RequestParam(name = "page") int page, @RequestParam(name = "mobile_number") String mobile_number)
    {
        Page<ShopKeeperOrders> pagedList = shop_keeper_orderService.getAllProducts(page, mobile_number);
        return pagedList.getContent();
    }

    @GetMapping("/getProductByCategories") //This is for getting products via specific categories in Ascending  order
    public List<OwoProduct> getProductByCategories(@RequestParam(name = "page") int page,
                                                   @RequestParam(name = "product_categories") Long[] product_categories)
    {
        List<Long> categories = Arrays.asList(product_categories);
        return productService.getProduct_by_categories(page, categories);
    }

    @GetMapping("/getProductBySpecificCategory")
    public List<OwoProduct> getProductsViaSpecificCategory(@RequestParam(name = "page") int page,
                                                           @RequestParam(name = "productCategory") Long productCategory)
    {
        return productService.getProductsOfSpecificCategory(page, productCategory);
    }

    @GetMapping("/getProductById")
    public OwoProduct getProductById(@RequestParam(name = "productId") Long productId)
    {
        return productService.getProductById(productId);
    }

    @GetMapping("/getBrandNameViaProductId")
    public String getBrandNameViaProductId(@RequestParam(name = "productId") Long productId)
    {
        return productService.getABrandViaProduct(productId);
    }

    @GetMapping("/getProductBySubCategory")
    public List<OwoProduct> getProductBySubcategory(@RequestParam("categoryIds") List<Long> categoryIds, @RequestParam("subCategoryName") String subCategoryName)
    {
        return productService.getProductsViaSubCategory(categoryIds, subCategoryName);
    }

    @GetMapping("/getSubCategoryIdViaName")
    public Long getSubcategoryId(@RequestParam("subCategoryName") String subCategoryName)
    {
        return subCategoryService.findSubCategoryId(subCategoryName);
    }

    @GetMapping("/getProductsBySubCategory")
    public List<OwoProduct> getSubCategoryBasedProducts(@RequestParam("page") int page,
                                                        @RequestParam("subCategoryId") Long subCategoryId)
    {
        return productService.getProducts(page, subCategoryId);
    }

    @GetMapping("/getSpecificCategoryData")
    public List<CategoryEntity> getSpecificCategoryData(@RequestParam("categoryIds") List<Long> categoryIds)
    {
        return categoryService.getSpecificCategoryData(categoryIds);
    }

    @GetMapping("/getBrandsViaCategory")
    public List<Brands> getBrandsViaCategory(@RequestParam(name = "number") int number, @RequestParam(name = "categoryIds") List<Long> categoryIds)
    {
        return brandsService.getBrandsViaCategory(number, categoryIds);
    }
}