package com.shopKpr.controller.admin;

import com.shopKpr.entity.admin.AdminPermissions;
import com.shopKpr.entity.admin.AdminLogin;
import com.shopKpr.entity.admin.AdminLoginWrapper;
import com.shopKpr.service.adminLogin.AdminLoginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin/adminLogin/v1/")
public class AdminRegistrationAndLoginControllerAdmin {

    private final AdminLoginService adminLoginService;

    public AdminRegistrationAndLoginControllerAdmin( AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @PostMapping("/addAnAdmin")
    public void addAnAdmin( @RequestBody AdminLoginWrapper adminLoginWrapper)
    {
        adminLoginService.addAnAdmin(adminLoginWrapper);
    }

    @GetMapping("/getAdminInfo")
    public AdminLoginWrapper getAdminCredential(@RequestParam(name = "adminEmailAddress") String adminEmailAddress)
    {
        return adminLoginService.getAdminInfo(adminEmailAddress);
    }

    @GetMapping("/getAllAdminInfo")
    public List<AdminLogin> getAllAdminInfo()
    {
        return adminLoginService.getAllAdminInfo();
    }

    @PutMapping("/updateAdminPermission")
    public void updateAdminPermissions(@RequestBody AdminLoginWrapper adminLoginWrapper)
    {
        adminLoginService.updateAdminPermissions(adminLoginWrapper);
    }

    @GetMapping("/getAdminPermissions")
    public List<AdminPermissions> getAdminAllPermissions( @RequestParam(name = "adminId") Integer adminId)
    {
        return adminLoginService.getAdminAllPermissions(adminId);
    }

    @DeleteMapping("/deleteAdmin")
    public void deleteAdmin(@RequestParam(name = "adminId") Integer adminId)
    {
        adminLoginService.deleteAdmin(adminId);
    }

}
