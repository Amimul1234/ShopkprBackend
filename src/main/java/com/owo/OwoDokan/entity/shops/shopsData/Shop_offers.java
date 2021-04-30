package com.owo.OwoDokan.entity.shops.shopsData;

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
public class Shop_offers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long offer_id;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String offer_imag_uri;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH
            , CascadeType.REFRESH})
    @JsonBackReference
    private Shops shops;
}
