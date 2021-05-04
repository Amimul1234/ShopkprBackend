package com.shopKpr.repository.timeSlot;

import com.shopKpr.entity.timeSlot.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepo extends JpaRepository<TimeSlot, Long> {
}
