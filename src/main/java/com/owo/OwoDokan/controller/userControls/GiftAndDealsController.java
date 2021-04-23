package com.owo.OwoDokan.controller.userControls;

import com.owo.OwoDokan.entity.deals.Deals;
import com.owo.OwoDokan.entity.gifts.Gifts;
import com.owo.OwoDokan.service.deal.DealService;
import com.owo.OwoDokan.service.gift.GiftService;
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
