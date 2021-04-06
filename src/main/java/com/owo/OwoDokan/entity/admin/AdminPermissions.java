package com.owo.OwoDokan.entity.admin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class AdminPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long permissionId;
    private String permission;

    @ManyToOne
    @JsonBackReference
    private AdminLogin adminLogin;
}
