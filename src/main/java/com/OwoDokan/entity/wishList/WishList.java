package com.OwoDokan.entity.wishList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishList
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wishListId;
    private Long productId;
    private Long userId;
}
