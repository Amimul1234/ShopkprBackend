package com.shopKpr.repository.cart;

import com.shopKpr.entity.cart.CartList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartList, String>{
}
