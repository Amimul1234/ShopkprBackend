package com.shopKpr.entity.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Shop_keeper_ordered_products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long product_id;
    @Column(nullable = false)
    private String product_name;
    @Column(nullable = false)
    private String product_category;
    @Column(nullable = false)
    private double product_price;
    @Column(nullable = false)
    private double product_discount;
    @Column(nullable = false)
    private int product_quantity;
    @Column(columnDefinition = "LONGTEXT")
    private String product_description;
    @Column(nullable = false)
    private String product_creation_date;
    @Column(nullable = false)
    private String product_creation_time;
    @Column(nullable = false)
    private String product_sub_category;
    private String product_brand;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String product_image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})

    @JsonBackReference
    private ShopKeeperOrders shopKeeperOrders;
}
