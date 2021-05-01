package com.shopKpr.controller.admin;

import com.shopKpr.entity.offers.OffersEntity;
import com.shopKpr.service.offer.OfferService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("shopKpr/admin")
public class BannerControllerAdmin
{
    private final OfferService offerService;

    public BannerControllerAdmin( OfferService offerService ) {
        this.offerService = offerService;
    }


    @PostMapping("/addAnOffer")
    public String addAnOffer(@RequestBody OffersEntity offersEntity)
    {
        return offerService.addANewOffer(offersEntity);
    }

    @DeleteMapping("/deleteOffer")
    public String deleteAnOffer(@RequestParam(name = "offerId") Long offerId)
    {
        return offerService.deleteOffer(offerId);
    }

    @GetMapping("/getAllOffers")
    public List<OffersEntity> getAllOffers()
    {
        return offerService.getAllOffers();
    }
}
