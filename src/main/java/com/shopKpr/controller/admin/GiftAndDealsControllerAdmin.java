package com.shopKpr.controller.admin;

import com.shopKpr.entity.deals.Deals;
import com.shopKpr.entity.gifts.Gifts;
import com.shopKpr.service.deal.DealService;
import com.shopKpr.service.gift.GiftService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("shopKpr/admin")
public class GiftAndDealsControllerAdmin
{
    private final GiftService giftService;
    private final DealService dealService;

    public GiftAndDealsControllerAdmin( GiftService giftService, DealService dealService ) {
        this.giftService = giftService;
        this.dealService = dealService;
    }

    @PostMapping("/createGiftCard")
    public void createGiftCard( @RequestBody Gifts gifts)
    {
        giftService.createGiftsCard(gifts);
    }

    @DeleteMapping("/deleteGiftCard")
    public void deleteGiftCard(@RequestParam("giftCardId") Long giftCardId)
    {
        giftService.deleteGiftCard(giftCardId);
    }

    @PostMapping("/createNewDeal")
    public void createNewDeal( @RequestBody Deals deals)
    {
        dealService.addNewDeal(deals);
    }

    @DeleteMapping("/deleteDeal")
    public void deleteDeal(@RequestParam("dealsId") Long dealsId)
    {
        dealService.deleteDeal(dealsId);
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
