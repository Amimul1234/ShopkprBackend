package com.OwoDokan.entity.shops.registration;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.OwoDokan.entity.shops.shopsData.Shops;
import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class ShopKeeperPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long permittedCategoryId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonBackReference
    private Shops shops;
}
