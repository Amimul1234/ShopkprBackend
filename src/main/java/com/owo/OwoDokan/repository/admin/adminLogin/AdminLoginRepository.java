package com.owo.OwoDokan.repository.admin.adminLogin;

import com.owo.OwoDokan.entity.admin.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepository extends JpaRepository<com.owo.OwoDokan.entity.admin.AdminLogin, Integer> {
    AdminLogin findByAdminEmailAddress( String adminEmailAddress );
}
