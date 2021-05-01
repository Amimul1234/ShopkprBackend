package com.shopKpr.controller.admin;

import com.shopKpr.entity.qupon.Qupon;
import com.shopKpr.service.qupon.QuponService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("shopKpr/admin")
public class QuponControllerAdmin
{
    private final QuponService quponService;

    public QuponControllerAdmin( QuponService quponService ) {
        this.quponService = quponService;
    }

    @PostMapping("/addNewQupon")
    public void addNewQupon( @RequestBody Qupon qupon)
    {
        quponService.addNewQupon(qupon);
    }

    @GetMapping("/getAllQupon")
    public List<Qupon> quponList()
    {
        return quponService.getAllQupons();
    }

    @DeleteMapping("/deleteQupon")
    public void deleteQupon(@RequestParam("quponId") Long quponId)
    {
        quponService.deleteQupon(quponId);
    }
}
