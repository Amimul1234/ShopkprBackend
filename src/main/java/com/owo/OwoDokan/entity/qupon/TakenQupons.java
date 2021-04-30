package com.owo.OwoDokan.entity.qupon;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.owo.OwoDokan.entity.registerAccount.ShopKeeperUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TakenQupons {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quponId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonBackReference
    private ShopKeeperUser shopKeeperUser;
}
