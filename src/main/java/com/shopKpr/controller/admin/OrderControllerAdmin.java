package com.shopKpr.controller.admin;

import com.shopKpr.entity.order.ShopKeeperOrders;
import com.shopKpr.service.order.ShopKeeperOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("shopKpr/admin")
public class OrderControllerAdmin
{
    private final ShopKeeperOrderService shopKeeperOrderService;

    public OrderControllerAdmin( ShopKeeperOrderService shopKeeperOrderService ) {
        this.shopKeeperOrderService = shopKeeperOrderService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getPendingOrders")
    public List<ShopKeeperOrders> getPendingOrders()
    {
        return shopKeeperOrderService.findAllByState("Pending");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getConfirmedOrders")
    public List<ShopKeeperOrders> getConfirmedOrders()
    {
        return shopKeeperOrderService.findAllByState("Confirmed");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getProcessingOrders")
    public List<ShopKeeperOrders> getProcessingOrders()
    {
        return shopKeeperOrderService.findAllByState("Processing");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getPickedOrders")
    public List<ShopKeeperOrders> getPickedOrders()
    {
        return shopKeeperOrderService.findAllByState("Picked");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getShippedOrders")
    public List<ShopKeeperOrders> getShippedOrders()
    {
        return shopKeeperOrderService.findAllByState("Shipped");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getDeliveredOrders")
    public ResponseEntity<List<ShopKeeperOrders>> getDeliveredOrders( @RequestParam("page_num") int page_num)
    {
        try
        {
            return new ResponseEntity<>(shopKeeperOrderService.findDeliveredOrders(page_num).getContent(), HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getCancelledOrders")
    public ResponseEntity<List<ShopKeeperOrders>> getCancelledOrders( @RequestParam("page_num") int page_num)
    {
        try
        {
            return new ResponseEntity<>(shopKeeperOrderService.findCancelledOrders(page_num).getContent(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/setOrderState")
    public void setOrderState(@RequestParam(name="order_id") long order_id,
                              @RequestParam("order_state") String order_state)
    {
        shopKeeperOrderService.setOrderState(order_id, order_state);
    }
}
