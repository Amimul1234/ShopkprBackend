package com.OwoDokan.controller.userControls;

import com.OwoDokan.service.wishList.WishListService;
import com.OwoDokan.entity.product.OwoProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishListController
{
    private final WishListService wishListService;

    public WishListController( WishListService wishListService ) {
        this.wishListService = wishListService;
    }

    //wish List
    @PostMapping("/addProductToWishList")
    public ResponseEntity<String> addProductToWishList( @RequestParam("shop_keeper_id") Long shop_keeper_id,
                                                        @RequestParam("product_id") Long productId)
    {
        return wishListService.addToWishList(shop_keeper_id, productId);
    }

    @DeleteMapping("/deleteWishListProduct")
    public void deleteWishListProduct(@RequestParam("shop_keeper_id") Long shop_keeper_id,
                                                        @RequestParam("product_id") Long productId)
    {
        wishListService.deleteFromWishList(shop_keeper_id, productId);
    }

    @GetMapping("/getAllWishListProductsId")
    public List<Long> getAllWishListProductsId(@RequestParam("user_id") Long userId)
    {
        return wishListService.getWishListProductIds(userId);
    }

    @GetMapping("/getAllWishListProducts")
    public List<OwoProduct> wishListProducts(@RequestParam("user_id") Long user_id)
    {
        return wishListService.getProducts(user_id);
    }
}
