package com.owo.OwoDokan.controller.userControls;

import com.owo.OwoDokan.entity.timeSlot.TimeSlot;
import com.owo.OwoDokan.service.timeSlot.TimeSlotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
