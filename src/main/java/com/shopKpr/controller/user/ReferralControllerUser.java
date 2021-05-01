package com.shopKpr.controller.user;

import com.shopKpr.entity.referral.Referral;
import com.shopKpr.service.referral.ReferralService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
public class ReferralControllerUser {

    private final ReferralService referralService;

    public ReferralControllerUser( ReferralService referralService ) {
        this.referralService = referralService;
    }

    @PostMapping("/addReferralPoint")
    public void addReferralPointForUser( @RequestParam("mobileNumber") String mobileNumber)
    {
        referralService.addNewPoint(mobileNumber);
    }

    @GetMapping("/getReferralPoint")
    public Referral getReferral(@RequestParam("mobileNumber") String mobileNumber)
    {
        return referralService.getReferral(mobileNumber);
    }
}
