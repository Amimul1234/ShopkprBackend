package com.shopKpr.repository.shop.registraton;

import com.shopKpr.entity.shops.shopsData.Shops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRegistrationRequestRepo extends JpaRepository<Shops, Long> {
}
