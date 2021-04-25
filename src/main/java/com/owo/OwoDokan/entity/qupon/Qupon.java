package com.owo.OwoDokan.entity.qupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Qupon {
    @Id
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
