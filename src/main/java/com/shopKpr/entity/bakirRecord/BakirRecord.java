package com.shopKpr.entity.bakirRecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Table(indexes = { @Index(name = "BakirIndex", columnList = "shopMobileNumber")})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BakirRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bakirRecordId;
    @Column(nullable = false)
    private String shopMobileNumber;
    private String customerName;
    private String productName;
    private String productPrice;
    private String date;
    private String paymentMethod;
}
