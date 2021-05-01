package com.shopKpr.controller.admin;

import com.shopKpr.entity.shopInfoChange.ChangeShopInfo;
import com.shopKpr.service.shopInfoChangeService.ShopInfoChangeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("shopKpr/admin")
public class ShopInfoChangeControllerAdmin
{

    private final ShopInfoChangeService shopInfoChangeService;

    public ShopInfoChangeControllerAdmin( ShopInfoChangeService shopInfoChangeService ) {
        this.shopInfoChangeService = shopInfoChangeService;
    }

    @GetMapping("/getAllChangeRequests")
    public List<ChangeShopInfo> getAllShopChangeRequests()
    {
        return shopInfoChangeService.getAllShopInfoChangeRequests();
    }

    @PostMapping("/approveShopInfoChange")
    public void approveShopInfoChange(@RequestBody ChangeShopInfo changeShopInfo)
    {
        shopInfoChangeService.approveShopInfoChange(changeShopInfo);
    }
}
