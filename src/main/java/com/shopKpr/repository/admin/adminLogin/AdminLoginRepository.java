package com.shopKpr.repository.admin.adminLogin;

import com.shopKpr.entity.admin.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminLoginRepository extends JpaRepository<AdminLogin, Integer> {
    Optional<AdminLogin> findByAdminEmailAddress( String adminEmailAddress );
}
