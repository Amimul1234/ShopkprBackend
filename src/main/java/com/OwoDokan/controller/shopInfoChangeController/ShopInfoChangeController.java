package com.OwoDokan.controller.shopInfoChangeController;

import com.OwoDokan.entity.shopInfoChange.ChangeShopInfo;
import com.OwoDokan.service.shopInfoChangeService.ShopInfoChangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopInfoChangeController {
    private final ShopInfoChangeService shopInfoChangeService;

    public ShopInfoChangeController( ShopInfoChangeService shopInfoChangeService ) {
        this.shopInfoChangeService = shopInfoChangeService;
    }

    @PostMapping("/makeShopInfoChangeRequest")
    public void makeShopInfoChangeRequest(@RequestBody ChangeShopInfo changeShopInfo)
    {
        shopInfoChangeService.makeChangeRequest(changeShopInfo);
    }

    @PostMapping("/approveShopInfoChange")
    public void approveShopInfoChange(@RequestBody ChangeShopInfo changeShopInfo)
    {
        shopInfoChangeService.approveShopInfoChange(changeShopInfo);
    }

    @GetMapping("/getAllChangeRequests")
    public List<ChangeShopInfo> getAllShopChangeRequests()
    {
        return shopInfoChangeService.getAllShopInfoChangeRequests();
    }
}
