package com.owo.OwoDokan.service.wishList;

import com.owo.OwoDokan.entity.wishList.WishList;
import com.owo.OwoDokan.repository.wishList.WishListRepository;
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

    public WishListService( WishListRepository wishListRepository ) {
        this.wishListRepository = wishListRepository;
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
}
