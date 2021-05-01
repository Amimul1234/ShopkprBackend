package com.shopKpr.service.adminLogin;

import com.shopKpr.entity.admin.AdminPermissions;
import com.shopKpr.entity.admin.AdminLogin;
import com.shopKpr.entity.admin.AdminLoginWrapper;
import com.shopKpr.repository.admin.adminLogin.AdminLoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AdminLoginService {

    private final AdminLoginRepository adminLoginRepository;

    public AdminLoginService( AdminLoginRepository adminLoginRepository ) {
        this.adminLoginRepository = adminLoginRepository;
    }

    public void addAnAdmin( AdminLoginWrapper adminLoginWrapper) {

        AdminLogin adminLogin = adminLoginWrapper.getAdminLogin();

        for(int i=0; i<adminLoginWrapper.getAdminPermissionsList().size(); i++)
            adminLoginWrapper.getAdminPermissionsList().get(i).setAdminLogin(adminLogin);

        adminLogin.setAdminPermissionsList(adminLoginWrapper.getAdminPermissionsList());

        try
        {
            adminLoginRepository.save(adminLogin);
        }catch (Exception e)
        {
            log.error("Error is: "+e.getMessage());
            throw new RuntimeException("Admin adding failed");
        }
    }

    public AdminLoginWrapper getAdminInfo( String adminEmailAddress ) {
        Optional<AdminLogin> adminLoginOptional = adminLoginRepository.findByAdminEmailAddress(adminEmailAddress);

        if(adminLoginOptional.isPresent())
        {
            AdminLogin adminLogin = adminLoginOptional.get();
            List<AdminPermissions> adminPermissionsList = adminLogin.getAdminPermissionsList();

            return new AdminLoginWrapper(adminLogin, adminPermissionsList);
        }
        else
        {
            log.error("Admin credential does not exists");
            throw new RuntimeException("Admin Credential does not exists");
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

    public List<AdminPermissions> getAdminAllPermissions( Integer adminId ) {
        try
        {
            Optional<AdminLogin> adminLoginOptional = adminLoginRepository.findById(adminId);

            if(adminLoginOptional.isPresent())
            {
                AdminLogin adminLogin = adminLoginOptional.get();
                return adminLogin.getAdminPermissionsList();
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

    public void updateAdminPermissions( AdminLoginWrapper adminLoginWrapper )
    {
        try
        {
            Optional<AdminLogin> adminLoginOptional = adminLoginRepository.findById(
                    adminLoginWrapper.getAdminLogin().getAdminId());

            if(adminLoginOptional.isPresent())
            {
                AdminLogin adminLogin = adminLoginOptional.get();

                for(int i=0; i<adminLoginWrapper.getAdminPermissionsList().size(); i++)
                    adminLoginWrapper.getAdminPermissionsList().get(i).setAdminLogin(adminLogin);

                adminLogin.getAdminPermissionsList().clear();
                adminLogin.getAdminPermissionsList().addAll(adminLoginWrapper.getAdminPermissionsList());

                adminLogin.setAdminName(adminLoginWrapper.getAdminLogin().getAdminName());
                adminLogin.setAdminEmailAddress(adminLoginWrapper.getAdminLogin().getAdminEmailAddress());

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
}
