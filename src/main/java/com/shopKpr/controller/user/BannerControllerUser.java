package com.shopKpr.controller.user;

import com.shopKpr.service.offer.OfferService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
public class BannerControllerUser {

    private final OfferService offerService;

    public BannerControllerUser( OfferService offerService ) {
        this.offerService = offerService;
    }

    @GetMapping("/getBannerForSelectedCategories")
    public List<String> bannerImages( @RequestParam("categoryIds") List<Long> categoryIds)
    {
        return offerService.getOfferImages(categoryIds);
    }
}
