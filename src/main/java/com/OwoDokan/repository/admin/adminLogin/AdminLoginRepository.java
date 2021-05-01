package com.OwoDokan.repository.admin.adminLogin;

import com.OwoDokan.entity.admin.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminLoginRepository extends JpaRepository<AdminLogin, Integer> {
    Optional<AdminLogin> findByAdminEmailAddress( String adminEmailAddress );
}
