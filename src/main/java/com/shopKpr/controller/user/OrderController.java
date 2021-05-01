package com.shopKpr.controller.user;

import com.shopKpr.service.order.ShopKeeperOrderService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final ShopKeeperOrderService shopKeeperOrderService;

    public OrderController( ShopKeeperOrderService shopKeeperOrderService ) {
        this.shopKeeperOrderService = shopKeeperOrderService;
    }
}
