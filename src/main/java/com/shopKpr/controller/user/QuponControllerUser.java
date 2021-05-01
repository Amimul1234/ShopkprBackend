package com.shopKpr.controller.user;

import com.shopKpr.entity.qupon.Qupon;
import com.shopKpr.service.qupon.QuponService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
public class QuponControllerUser
{
    private final QuponService quponService;

    public QuponControllerUser( QuponService quponService ) {
        this.quponService = quponService;
    }

    @GetMapping("/getAQupon")
    public Qupon getAQupon( @RequestParam("quponId") Long quponId)
    {
        return quponService.getQuponById(quponId);
    }
}
