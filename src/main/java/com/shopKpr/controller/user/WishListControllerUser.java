package com.shopKpr.controller.user;

import com.shopKpr.service.wishList.WishListService;
import com.shopKpr.entity.product.OwoProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
public class WishListControllerUser
{
    private final WishListService wishListService;

    public WishListControllerUser( WishListService wishListService ) {
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
