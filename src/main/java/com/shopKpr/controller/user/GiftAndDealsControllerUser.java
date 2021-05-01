package com.shopKpr.controller.user;

import com.shopKpr.entity.deals.Deals;
import com.shopKpr.entity.gifts.Gifts;
import com.shopKpr.service.deal.DealService;
import com.shopKpr.service.gift.GiftService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class GiftAndDealsControllerUser {

    private final GiftService giftService;
    private final DealService dealService;

    public GiftAndDealsControllerUser( GiftService giftService, DealService dealService ) {
        this.giftService = giftService;
        this.dealService = dealService;
    }

    @GetMapping("/getAllGiftCards")
    public List<Gifts> getAllGiftCard()
    {
        return giftService.getAllGiftsCard();
    }

    @GetMapping("/getAllDeals")
    public List<Deals> getAllDeals()
    {
        return dealService.getAllDeals();
    }
}
