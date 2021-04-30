package com.owo.OwoDokan.entity.deals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Deals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealsId;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String dealDetails;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String dealImage;
}
