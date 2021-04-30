package com.owo.OwoDokan.entity.shops.shopsData;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table
public class UserDebts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String userName;
    private String userMobileNumber;
    private double userTotalDebt;
    private double userPaid;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH
            , CascadeType.REFRESH})
    @JsonBackReference
    private Shops shops;

    @OneToMany(
            mappedBy = "userDebts",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH
            , CascadeType.REFRESH}
    )
    @JsonIgnore
    private List<User_debt_details> userDebtDetails = new ArrayList<>();
}
