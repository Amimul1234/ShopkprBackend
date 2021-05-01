package com.shopKpr.controller.admin;

import com.shopKpr.service.timeSlot.TimeSlotService;
import com.shopKpr.entity.timeSlot.TimeSlot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/shopKpr/admin")
public class TimeSlotAdmin
{
    private final TimeSlotService timeSlotService;

    public TimeSlotAdmin( TimeSlotService timeSlotService )
    {
        this.timeSlotService = timeSlotService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addNewTimeSlot")
    public void addNewTimeSlot( @RequestBody TimeSlot timeSlot )
    {
         timeSlotService.addNewTimeSlot(timeSlot);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteTimeSlot")
    public void deleteTimeSlot( @RequestParam("timeSlotId") Long timeSlotId)
    {
        timeSlotService.deleteTimeSlot(timeSlotId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllAvailableTimeSlots")
    public List<TimeSlot> getAllAvailableTimeSlots()
    {
        return timeSlotService.getAllTimeSlots();
    }

}
