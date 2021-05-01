package com.shopKpr.repository.order;

import com.shopKpr.entity.order.ShopKeeperOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Order_repo extends JpaRepository<ShopKeeperOrders, Integer> {
    @Query("SELECT e FROM ShopKeeperOrders e WHERE e.shop_phone = :mobile_number")
    Page<ShopKeeperOrders> findByMobileNumber( @Param("mobile_number") String mobile_number, Pageable pageable);

    @Query("SELECT e FROM ShopKeeperOrders e WHERE e.shipping_state = :state")
    List<ShopKeeperOrders> findAllByState( @Param("state") String state);

    @Query("SELECT e FROM ShopKeeperOrders e WHERE e.order_number = :order_number")
    ShopKeeperOrders findOrderById( @Param("order_number") long order_number);

    @Query("SELECT e FROM ShopKeeperOrders e WHERE e.shipping_state = :state")
    Page<ShopKeeperOrders> findByCancelledOrders( @Param("state") String cancelled, Pageable pageable);
}
