package com.OwoDokan.entity.timeSlot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeSlotId;
    private String timeSlotString;
}
