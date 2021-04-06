package com.owo.OwoDokan.repository.cart;

import com.owo.OwoDokan.entity.cart.CartListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepo extends JpaRepository<CartListProduct, Long>{
}
