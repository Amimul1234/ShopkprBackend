package com.owo.OwoDokan.entity.registerAccount;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.owo.OwoDokan.entity.qupon.TakenQupons;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = {@Index(columnList = "mobileNumber", name = "mobileNumber")})
public class ShopKeeperUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shopKeeperId;
    private String name;
    private String mobileNumber;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String pin;
    @Column(columnDefinition = "LONGTEXT")
    private String imageUri;
    private Boolean accountEnabled;

    @OneToMany(
            mappedBy = "shopKeeperUser",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            orphanRemoval = true, fetch = FetchType.EAGER
    )
    @JsonManagedReference
    List<TakenQupons> takenQuponsList = new ArrayList<>();
}
