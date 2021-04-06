package com.owo.OwoDokan.controller.adminLogin;

import com.owo.OwoDokan.entity.admin.AdminLogin;
import com.owo.OwoDokan.entity.admin.AdminPermissions;
import com.owo.OwoDokan.service.adminLogin.AdminLoginService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/adminLogin/v1/")
public class Login {

    private final AdminLoginService adminLoginService;

    public Login(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @PostMapping("/addAnAdmin")
    public void addAnAdmin(@RequestBody AdminLogin adminLogin)
    {
        adminLoginService.addAnAdmin(adminLogin);
    }

    @GetMapping("/getAdminInfo")
    public AdminLogin getAdminCredential(@RequestParam(name = "adminEmailAddress") String adminEmailAddress)
    {
        return adminLoginService.getAdminInfo(adminEmailAddress);
    }

    @GetMapping("/getAllAdminInfo")
    public List<AdminLogin> getAllAdminInfo()
    {
        return adminLoginService.getAllAdminInfo();
    }

    @PutMapping("/updateAdminPermission")
    public void updateAdminPermissions(@RequestParam(name = "adminId") Integer adminId,
                                        @RequestBody List<AdminPermissions> adminPermissionsList)
    {
        adminLoginService.updateAdminPermissions(adminId, adminPermissionsList);
    }

    @DeleteMapping("/deleteAdmin")
    public void deleteAdmin(@RequestParam(name = "adminId") Integer adminId)
    {
        adminLoginService.deleteAdmin(adminId);
    }

}
