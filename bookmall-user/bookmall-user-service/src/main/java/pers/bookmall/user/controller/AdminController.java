package pers.bookmall.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.bookmall.user.pojo.Admin;
import pers.bookmall.user.service.AdminService;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("check")
    public ResponseEntity<Boolean> checkAdmin(Admin admin){
        return ResponseEntity.ok(adminService.checkAdmin(admin));
    }

}
