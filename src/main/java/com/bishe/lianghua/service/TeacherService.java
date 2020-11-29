package com.bishe.lianghua.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.lianghua.entity.Admin;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.entity.Teacher;

public interface TeacherService extends IService<Teacher> {

    R verifyLogin(String username, String password);
    R info(int teaId);
    R getPage(int pageNum, int pageSize);
    R getPage(int pageNum, int pageSize, String matchStr);
    R getMyCourseAllinfo(int teaId);
}
