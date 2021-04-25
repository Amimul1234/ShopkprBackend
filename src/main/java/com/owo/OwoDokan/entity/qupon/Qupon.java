package com.owo.OwoDokan.entity.qupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Qupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quponId;
    private double discount;
    @Column(nullable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy hh:mm:ss a", timezone = "Asia/Dhaka")
    private Date quponStartDate;
    @Column(nullable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy hh:mm:ss a", timezone = "Asia/Dhaka")
    private Date quponEndDate;
    private boolean enabled;
}
