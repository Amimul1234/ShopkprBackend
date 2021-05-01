package com.OwoDokan.repository.shop.shopUserRepo;

import com.OwoDokan.entity.shops.shopsData.UserDebts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDebt extends JpaRepository<UserDebts, Long>
{
    @Query("SELECT e from UserDebts e WHERE e.userId = :user_id")
    UserDebts findByUserId(@Param("user_id") Long user_id);

    @Query("SELECT e from UserDebts e WHERE e.userId = :user_id")
    UserDebts findByUserIdMobile(@Param("user_id") Long user_id);

    @Query("SELECT e from UserDebts e WHERE e.userMobileNumber = :mobileNumber")
    Optional<UserDebts> findByUserMobileNumber( @Param("mobileNumber") String mobileNumber );
}
