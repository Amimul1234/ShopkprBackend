package com.shopKpr.controller.user;

import com.shopKpr.entity.shops.shopsData.Shops;
import com.shopKpr.service.registration.ShopAddingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopController {

    private final ShopAddingService shopAddingService;

    public ShopController( ShopAddingService shopAddingService ) {
        this.shopAddingService = shopAddingService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getShopInfo")
    public Shops get_shop_info(@RequestParam(name = "shop_phone") String shop_phone) {
        return shopAddingService.getShopInfo(shop_phone);
    }

}
