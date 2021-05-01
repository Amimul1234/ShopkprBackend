package com.shopKpr.repository.cart;

import com.shopKpr.entity.cart.CartListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepo extends JpaRepository<CartListProduct, Long>{
}
