package com.owo.OwoDokan.controller.adminControls.offerManagement;

import com.owo.OwoDokan.entity.offers.OffersEntity;
import com.owo.OwoDokan.service.offer.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferManagement
{
    private final OfferService offerService;

    public OfferManagement( OfferService offerService ) {
        this.offerService = offerService;
    }

    @GetMapping("/getAllOffers")
    public List<OffersEntity> getAllOffers()
    {
        return offerService.getAllOffers();
    }

    @GetMapping("/getBannerForSelectedCategories")
    public List<String> bannerImages(@RequestParam("categoryIds") List<Long> categoryIds)
    {
        return offerService.getOfferImages(categoryIds);
    }

    @PostMapping("/addAnOffer")
    public String addAnOffer(@RequestBody OffersEntity offersEntity)
    {
        return offerService.addANewOffer(offersEntity);
    }

    @PutMapping("/updateAnOffer")
    public String updateAnOffer(@RequestBody OffersEntity offersEntity)
    {
        return offerService.updateExistenceOffer(offersEntity);
    }

    @DeleteMapping("/deleteOffer")
    public String deleteAnOffer(@RequestParam(name = "offerId") Long offerId)
    {
        return offerService.deleteOffer(offerId);
    }
}
