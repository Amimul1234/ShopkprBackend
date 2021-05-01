package com.OwoDokan.controller.quponController;

import com.OwoDokan.entity.qupon.Qupon;
import com.OwoDokan.service.qupon.QuponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuponController
{
    private final QuponService quponService;

    public QuponController( QuponService quponService ) {
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

    @GetMapping("/getAQupon")
    public Qupon getAQupon(@RequestParam("quponId") Long quponId)
    {
        return quponService.getQuponById(quponId);
    }
}
