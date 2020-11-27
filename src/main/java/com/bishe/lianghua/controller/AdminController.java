package com.bishe.lianghua.controller;

import com.bishe.lianghua.entity.Admin;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public R login(@RequestBody Admin admin){
        R r = adminService.verifyLogin(admin);
        return r;
    }

    /**
     * 获取用户基本信息
     * @return
     */
    @GetMapping("/info")
    public R info(@RequestHeader String LHToken){
        return new R(adminService.getById(LHToken));
    }

    @GetMapping("/logout")
    public R logout(){

        return new R();
    }

    @GetMapping("/getall")
    public R getAllAdmin(){
        List<Admin> list = adminService.list();
        R r = new R(list);
        return r;
    }

}
