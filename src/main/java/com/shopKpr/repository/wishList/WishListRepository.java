package com.shopKpr.repository.wishList;

import com.shopKpr.entity.wishList.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long>
{
    @Query(value = "SELECT * FROM wish_list where user_id = :user_id and product_id = :product_id", nativeQuery = true)
    Optional<WishList> findByShopAndProductId( @Param("user_id") Long shop_keeper_id, @Param("product_id") Long productId );

    Optional<List<WishList>> findByUserId(Long userId);
}
