package com.OwoDokan.repository.cart;

import com.OwoDokan.entity.cart.CartListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepo extends JpaRepository<CartListProduct, Long>{
}
