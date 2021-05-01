package com.shopKpr.controller.user;

import com.shopKpr.entity.shopInfoChange.ChangeShopInfo;
import com.shopKpr.service.shopInfoChangeService.ShopInfoChangeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
public class ShopInfoChangeControllerUser {

    private final ShopInfoChangeService shopInfoChangeService;

    public ShopInfoChangeControllerUser( ShopInfoChangeService shopInfoChangeService ) {
        this.shopInfoChangeService = shopInfoChangeService;
    }

    @PostMapping("/makeShopInfoChangeRequest")
    public void makeShopInfoChangeRequest(@RequestBody ChangeShopInfo changeShopInfo)
    {
        shopInfoChangeService.makeChangeRequest(changeShopInfo);
    }
}
