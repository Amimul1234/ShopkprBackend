package com.owo.OwoDokan.controller.userControls;

import com.owo.OwoDokan.entity.timeSlot.TimeSlot;
import com.owo.OwoDokan.service.timeSlot.TimeSlotService;
import org.springframework.data.jpa.repository.Query;
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

    @PostMapping("/addNewTimeSlot")
    public void addNewTimeSlot( @RequestBody TimeSlot timeSlot )
    {
         timeSlotService.addNewTimeSlot(timeSlot);
    }

    @GetMapping("/getAllAvailableTimeSlots")
    public List<TimeSlot> getAllAvailableTimeSlots()
    {
        return timeSlotService.getAllTimeSlots();
    }

    @DeleteMapping("/deleteTimeSlot")
    public void deleteTimeSlot( @RequestParam("timeSlotId") Long timeSlotId)
    {
        timeSlotService.deleteTimeSlot(timeSlotId);
    }

}
