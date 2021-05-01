package com.OwoDokan.repository.cart;

import com.OwoDokan.entity.cart.CartList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartList, String>{
}
