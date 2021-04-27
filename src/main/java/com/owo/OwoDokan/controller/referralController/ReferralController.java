package com.owo.OwoDokan.controller.referralController;

import com.owo.OwoDokan.entity.referral.Referral;
import com.owo.OwoDokan.service.referral.ReferralService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReferralController {

    private final ReferralService referralService;

    public ReferralController( ReferralService referralService ) {
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
