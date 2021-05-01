package com.shopKpr.controller.admin;

import com.shopKpr.entity.shops.shopsData.Shops;
import com.shopKpr.service.registration.ShopAddingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("shopKpr/admin")
public class ShopManagement
{
    private final ShopAddingService shopAddingService;

    public ShopManagement( ShopAddingService shopAddingService ) {
        this.shopAddingService = shopAddingService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/approveShop")
    public Shops approveShop( @RequestBody Shops shops)
    {
        return shopAddingService.approveNewShop(shops);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllShopRegistrationRequests")
    public List<Shops> getAllShopRegistrationRequests( @RequestParam("pageNumber") int pageNumber)
    {
        return shopAddingService.getAllShopRegistrationRequests(pageNumber);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllRegisteredShops")
    public List<Shops> getAllRegisteredShops(@RequestParam("pageNumber") int pageNumber)
    {
        return shopAddingService.getAllRegisteredShops(pageNumber);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getShopInfo")
    public Shops get_shop_info(@RequestParam(name = "shop_phone") String shop_phone) {
        return shopAddingService.getShopInfo(shop_phone);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateShopInfo")
    public Shops updateShop(@RequestBody Shops  shops)
    {
        return shopAddingService.updateShop(shops);
    }

}
