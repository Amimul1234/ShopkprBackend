package com.owo.OwoDokan.entity.gifts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gifts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long giftId;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String giftImage;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String giftDetails;
}
