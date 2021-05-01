package com.OwoDokan.repository.shop.registraton;

import com.OwoDokan.entity.shops.shopsData.Shops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRegistrationRequestRepo extends JpaRepository<Shops, Long> {
}
