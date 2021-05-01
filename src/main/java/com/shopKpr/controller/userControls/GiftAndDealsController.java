package com.shopKpr.controller.userControls;

import com.shopKpr.entity.deals.Deals;
import com.shopKpr.entity.gifts.Gifts;
import com.shopKpr.service.deal.DealService;
import com.shopKpr.service.gift.GiftService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class GiftAndDealsController
{
    private final GiftService giftService;
    private final DealService dealService;

    public GiftAndDealsController( GiftService giftService, DealService dealService ) {
        this.giftService = giftService;
        this.dealService = dealService;
    }

    @PostMapping("/createGiftCard")
    public void createGiftCard( @RequestBody Gifts gifts)
    {
        giftService.createGiftsCard(gifts);
    }

    @GetMapping("/getAllGiftCards")
    public List<Gifts> getAllGiftCard()
    {
        return giftService.getAllGiftsCard();
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

    @GetMapping("/getAllDeals")
    public List<Deals> getAllDeals()
    {
        return dealService.getAllDeals();
    }

    @DeleteMapping("/deleteDeal")
    public void deleteDeal(@RequestParam("dealsId") Long dealsId)
    {
        dealService.deleteDeal(dealsId);
    }
}
