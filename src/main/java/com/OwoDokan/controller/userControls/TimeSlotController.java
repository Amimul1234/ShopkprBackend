package com.OwoDokan.controller.userControls;

import com.OwoDokan.service.timeSlot.TimeSlotService;
import com.OwoDokan.entity.timeSlot.TimeSlot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TimeSlotController
{
    private final TimeSlotService timeSlotService;

    public TimeSlotController( TimeSlotService timeSlotService )
    {
        this.timeSlotService = timeSlotService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("shopKpr/admin/addNewTimeSlot")
    public void addNewTimeSlot( @RequestBody TimeSlot timeSlot )
    {
         timeSlotService.addNewTimeSlot(timeSlot);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("shopKpr/admin/deleteTimeSlot")
    public void deleteTimeSlot( @RequestParam("timeSlotId") Long timeSlotId)
    {
        timeSlotService.deleteTimeSlot(timeSlotId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/getAllAvailableTimeSlots")
    public List<TimeSlot> getAllAvailableTimeSlots()
    {
        return timeSlotService.getAllTimeSlots();
    }

}
