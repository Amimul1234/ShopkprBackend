package com.shopKpr.entity.shopInfoChange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeShopInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long changeShopInfoId;
    private String shopOwnerMobileNumber;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String newShopImageURL;
    @Column(nullable = false)
    private String newShopName;
    @Column(nullable = false)
    private String newShopAddress;
    @Column(nullable = false)
    private String newShopOwnerName;
    @Column(nullable = false)
    private String newShopServiceMobile;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String newShopOwnerNidFront;
    @Column(columnDefinition = "LONGTEXT")
    private String newShopTradeLicenseURI;
}
