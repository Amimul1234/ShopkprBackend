package com.shopKpr.controller.user;

import com.shopKpr.service.timeSlot.TimeSlotService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController( TimeSlotService timeSlotService )
    {
        this.timeSlotService = timeSlotService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getAllAvailableTimeSlots")
    public List<com.shopKpr.entity.timeSlot.TimeSlot> getAllAvailableTimeSlots()
    {
        return timeSlotService.getAllTimeSlots();
    }
}
