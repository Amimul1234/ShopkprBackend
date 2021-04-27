package com.owo.OwoDokan.repository.shopInfoChangeRepository;

import com.owo.OwoDokan.entity.shopInfoChange.ChangeShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopInfoChangeRepo extends JpaRepository<ChangeShopInfo, Long> {
}
