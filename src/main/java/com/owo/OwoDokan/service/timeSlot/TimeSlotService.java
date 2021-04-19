package com.owo.OwoDokan.service.timeSlot;

import com.owo.OwoDokan.entity.timeSlot.TimeSlot;
import com.owo.OwoDokan.repository.timeSlot.TimeSlotRepo;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotService
{
    private final TimeSlotRepo timeSlotRepo;

    public TimeSlotService( TimeSlotRepo timeSlotRepo ) {
        this.timeSlotRepo = timeSlotRepo;
    }

    public void addNewTimeSlot( TimeSlot timeSlot ) {
        timeSlotRepo.save(timeSlot);
    }
}
