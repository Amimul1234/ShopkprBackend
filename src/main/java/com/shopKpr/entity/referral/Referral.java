package com.shopKpr.entity.referral;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Referral {
    @Id
    private String referrerNumber;
    private Long referPoint;
}
