package com.shopKpr.controller.user;

import com.shopKpr.entity.qupon.Qupon;
import com.shopKpr.entity.registerAccount.ShopKeeperUser;
import com.shopKpr.entity.registerAccount.UserShopKeeper;
import com.shopKpr.service.registration.ShopKeeperRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class ShopUserControllerUser {

    private final ShopKeeperRegistrationService shopKeeperRegistrationService;

    public ShopUserControllerUser( ShopKeeperRegistrationService shopKeeperRegistrationService ) {
        this.shopKeeperRegistrationService = shopKeeperRegistrationService;
    }

    @GetMapping("/getShopKeeper")
    public ShopKeeperUser getShopKeeper( @RequestParam(name = "mobileNumber") String mobileNumber)
    {
        return shopKeeperRegistrationService.getShopKeeper(mobileNumber);
    }

    @PostMapping("/registerShopKeeper")
    public String registerShopKeeper(@RequestBody UserShopKeeper userShopKeeper)
    {
        return shopKeeperRegistrationService.addNewShopKeeper(userShopKeeper);
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
