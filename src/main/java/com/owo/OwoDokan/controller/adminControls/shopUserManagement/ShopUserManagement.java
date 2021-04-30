package com.owo.OwoDokan.controller.adminControls.shopUserManagement;

import com.owo.OwoDokan.entity.qupon.Qupon;
import com.owo.OwoDokan.entity.registerAccount.ShopKeeperUser;
import com.owo.OwoDokan.entity.registerAccount.UserShopKeeper;
import com.owo.OwoDokan.service.registration.ShopKeeperRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ShopUserManagement
{
    private final ShopKeeperRegistrationService shopKeeperRegistrationService;

    public ShopUserManagement( ShopKeeperRegistrationService shopKeeperRegistrationService ) {
        this.shopKeeperRegistrationService = shopKeeperRegistrationService;
    }

    @GetMapping("/getShopKeeper")
    public ShopKeeperUser getShopKeeper( @RequestParam(name = "mobileNumber") String mobileNumber)
    {
        return shopKeeperRegistrationService.getShopKeeper(mobileNumber);
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

    @PostMapping("/registerShopKeeper")
    public String registerShopKeeper(@RequestBody UserShopKeeper userShopKeeper)
    {
        return shopKeeperRegistrationService.addNewShopKeeper(userShopKeeper);
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

    @PutMapping("/updateShopKeeperInfo")
    public String updateShopKeeperInfo(@RequestBody ShopKeeperUser shopKeeperUser)
    {
        return shopKeeperRegistrationService.updateShopInfo(shopKeeperUser);
    }

    @PostMapping("/addQuponToAnUser")
    public void addNewCouponToUser( @RequestParam("mobileNumber") String mobileNumber, @RequestBody Qupon qupon )
    {
        shopKeeperRegistrationService.addNewCouponToUser(mobileNumber, qupon);
    }

    @GetMapping("/checkUserAlreadyTakenCouponOrNot")
    public ResponseEntity<String> checkUserAlreadyTakenCoupon( @RequestParam("mobileNumber") String mobileNumber, @RequestParam("quponId") Long quponId)
    {
        return shopKeeperRegistrationService.checkTakenOrNotTaken(mobileNumber, quponId);
    }

    @PutMapping("/updateShopKeeperPin")
    public void changeUserPin(@RequestBody UserShopKeeper userShopKeeper)
    {
        shopKeeperRegistrationService.updatePin(userShopKeeper);
    }
}
