package com.shopKpr.controller.admin;

import com.shopKpr.entity.order.ShopKeeperOrders;
import com.shopKpr.service.order.ShopKeeperOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class OrderManagement
{
    private final ShopKeeperOrderService shopKeeperOrderService;

    public OrderManagement( ShopKeeperOrderService shopKeeperOrderService ) {
        this.shopKeeperOrderService = shopKeeperOrderService;
    }

    @GetMapping("/getPendingOrders")
    public ResponseEntity getPendingOrders()
    {
        return shopKeeperOrderService.findAllByState("Pending");
    }

    @GetMapping("/getConfirmedOrders")
    public ResponseEntity getConfirmedOrders()
    {
        return shopKeeperOrderService.findAllByState("Confirmed");
    }

    @GetMapping("/getProcessingOrders")
    public ResponseEntity getProcessingOrders()
    {
        return shopKeeperOrderService.findAllByState("Processing");
    }

    @GetMapping("/getPickedOrders")
    public ResponseEntity getPickedOrders()
    {
        return shopKeeperOrderService.findAllByState("Picked");
    }

    @GetMapping("/getShippedOrders")
    public ResponseEntity getShippedOrders()
    {
        return shopKeeperOrderService.findAllByState("Shipped");
    }

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

    @PutMapping("/setOrderState")
    public void setOrderState(@RequestParam(name="order_id") long order_id,
                                        @RequestParam("order_state") String order_state)
    {
        shopKeeperOrderService.setOrderState(order_id, order_state);
    }
}
