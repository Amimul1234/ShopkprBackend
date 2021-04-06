package com.owo.OwoDokan.repository.cart;

import com.owo.OwoDokan.entity.cart.CartList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartList, String>{
}
