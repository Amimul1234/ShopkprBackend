package com.shopKpr.controller.admin;

import com.shopKpr.service.registration.ShopKeeperRegistrationService;
import com.shopKpr.entity.registerAccount.ShopKeeperUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("shopKpr/admin")
public class ShopUserControllerAdmin
{
    private final ShopKeeperRegistrationService shopKeeperRegistrationService;

    public ShopUserControllerAdmin( ShopKeeperRegistrationService shopKeeperRegistrationService ) {
        this.shopKeeperRegistrationService = shopKeeperRegistrationService;
    }

    @GetMapping("/getAllEnabledShopKeepers")
    public List<ShopKeeperUser> getAllEnabledShopKeepers( @RequestParam(name = "page") int page)
    {
        return shopKeeperRegistrationService.findAllEnabledShopKeeper(page);
    }

    @GetMapping("/getAllDisabledAccounts")
    public List<ShopKeeperUser> getAllDisabledAccounts(@RequestParam(name = "page") int page)
    {
        return shopKeeperRegistrationService.findAllDisabledShopKeeper(page);
    }

    @PutMapping("/disableShopKeeper")
    public String disableShopKeeper(@RequestParam("mobileNumber") String mobileNumber)
    {
        return shopKeeperRegistrationService.disableShopKeeper(mobileNumber);
    }

    @PutMapping("/enableShopKeeper")
    public String enableShopKeeper(@RequestParam("mobileNumber") String mobileNumber)
    {
        return shopKeeperRegistrationService.enableShopKeeper(mobileNumber);
    }

    @DeleteMapping("/deleteShopKeeper")
    public String deleteShopKeeper(@RequestParam("mobileNumber") String mobileNumber)
    {
        return shopKeeperRegistrationService.deleteShopKeeper(mobileNumber);
    }
}
