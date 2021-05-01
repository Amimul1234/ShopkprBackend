package com.OwoDokan.repository.order;

import com.OwoDokan.entity.order.Shop_keeper_ordered_products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ordered_products_repo extends JpaRepository<Shop_keeper_ordered_products, Integer> {
}
