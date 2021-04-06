package com.owo.OwoDokan.service.adminLogin;

import com.owo.OwoDokan.entity.admin.AdminLogin;
import com.owo.OwoDokan.entity.admin.AdminPermissions;
import com.owo.OwoDokan.repository.admin.adminLogin.AdminLoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminLoginService {

    private final AdminLoginRepository adminLoginRepository;

    public AdminLoginService( AdminLoginRepository adminLoginRepository ) {
        this.adminLoginRepository = adminLoginRepository;
    }

    public void addAnAdmin( AdminLogin adminLogin) {
        try
        {
            adminLoginRepository.save(adminLogin);
        }catch (Exception e)
        {
            log.error("Error is: "+e.getMessage());
            throw new RuntimeException("Admin adding failed");
        }
    }

    public AdminLogin getAdminInfo( String adminEmailAddress ) {
        try
        {
            return adminLoginRepository.findByAdminEmailAddress(adminEmailAddress);
        }catch (Exception e)
        {
            log.error("Error is:- " + e.getMessage());
            throw new RuntimeException("Admin Credential fetching error");
        }
    }

    public void updateAdminPermissions( Integer adminId, List<AdminPermissions> adminPermissionsList ) {
        try
        {
            Optional<AdminLogin> adminLoginOptional = adminLoginRepository.findById(adminId);

            if(adminLoginOptional.isPresent())
            {
                AdminLogin adminLogin = adminLoginOptional.get();

                adminLogin.setAdminPermissionsList(adminPermissionsList);

                adminLoginRepository.save(adminLogin);
            }
            else
            {
                log.info("Wrong admin id provided");
                throw new RuntimeException("Admin does not exists");
            }
        }catch (Exception e)
        {
            log.error("Can not fetch admin data from database, Error is: "+e.getMessage());
            throw new RuntimeException("Admin data can not be fetched");
        }
    }

    public void deleteAdmin( Integer adminId ) {
        try {
            adminLoginRepository.deleteById(adminId);
        }
        catch (Exception e)
        {
            log.error("Error while deleting admin, Error is: "+e.getMessage());
            throw new RuntimeException("Can not delete admin");
        }
    }

    public List<AdminLogin> getAllAdminInfo() {
        return adminLoginRepository.findAll();
    }
}
