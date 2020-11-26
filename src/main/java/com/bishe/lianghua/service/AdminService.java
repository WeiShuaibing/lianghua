package com.bishe.lianghua.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.lianghua.entity.Admin;
import com.bishe.lianghua.entity.R;

public interface AdminService extends IService<Admin> {

    public R verifyLogin(Admin admin);

}
