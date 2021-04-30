package com.owo.OwoDokan.entity.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(indexes = {@Index(name = "adminEmail", columnList = "adminEmailAddress")})
public class AdminLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    private String adminName;
    private String adminEmailAddress;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String adminPassword;

    @OneToMany(mappedBy = "adminLogin",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AdminPermissions> adminPermissionsList = new ArrayList<>();
}
