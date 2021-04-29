package com.owo.OwoDokan.entity.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;
    private String notificationName;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String notificationImage;
}
