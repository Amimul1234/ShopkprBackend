package com.OwoDokan.service.wishList;

import com.OwoDokan.entity.wishList.WishList;
import com.OwoDokan.entity.product.OwoProduct;
import com.OwoDokan.repository.wishList.WishListRepository;
import com.OwoDokan.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService
{
    private final WishListRepository wishListRepository;
    private final ProductService productService;

    public WishListService( WishListRepository wishListRepository, ProductService productService ) {
        this.wishListRepository = wishListRepository;
        this.productService = productService;
    }


    public ResponseEntity<String> addToWishList( Long shop_keeper_id, Long productId )
    {
        WishList wishList = new WishList();

        wishList.setProductId(productId);
        wishList.setUserId(shop_keeper_id);

        wishListRepository.save(wishList);

        return new ResponseEntity<>("Added to wishlist", HttpStatus.OK);
    }

    public void deleteFromWishList( Long shop_keeper_id, Long productId )
    {
        Optional<WishList> wishListOptional = wishListRepository.findByShopAndProductId(shop_keeper_id,
                productId);

        if(wishListOptional.isPresent())
        {
            WishList wishList = wishListOptional.get();
            wishListRepository.delete(wishList);
        }
        else
        {
            throw new RuntimeException("Wish list with product id: "+productId+ " does not exists");
        }
    }

    public List<Long> getWishListProductIds( Long userId )
    {
        Optional<List<WishList>> optionalWishLists = wishListRepository.findByUserId(userId);

        if(optionalWishLists.isPresent())
        {
            List<WishList> wishLists = optionalWishLists.get();
            List<Long> productIds = new ArrayList<>();

            wishLists.forEach(wishList -> productIds.add(wishList.getProductId()));
            return productIds;
        }
        else
        {
            throw new RuntimeException("User with id: " + userId + " does not exists");
        }
    }

    public List<OwoProduct> getProducts( Long user_id )
    {
        Optional<List<WishList>> optionalWishLists = wishListRepository.findByUserId(user_id);

        if(optionalWishLists.isPresent())
        {
            List<WishList> wishLists = optionalWishLists.get();
            List<Long> productIds = new ArrayList<>();

            wishLists.forEach(wishList -> productIds.add(wishList.getProductId()));

            List<OwoProduct> owoProductList = new ArrayList<>();

            productIds.forEach(productId ->
                    owoProductList.add(productService.getProductById(productId)));

            owoProductList.forEach(this::responseManipulator);

            return owoProductList;
        }
        else
        {
            throw new RuntimeException("User with id: " + user_id + " does not exists");
        }
    }


    private void responseManipulator(OwoProduct owoProduct)
    {
        owoProduct.setProductDescription(null);
        owoProduct.setProductCategoryId(null);
        owoProduct.setProductCreationTime(null);
        owoProduct.setProductCreationDate(null);
    }

}
