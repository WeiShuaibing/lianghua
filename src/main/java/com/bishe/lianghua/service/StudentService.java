package com.bishe.lianghua.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.entity.Student;
import com.bishe.lianghua.entity.Teacher;

import java.util.List;

public interface StudentService extends IService<Student> {

    Page<Student> getPage(int pageNum, int pageSize);
    Page<Student> getPage(int pageNum, int pageSize, String matchStr);

    Page<Student> getPage(int pageNum, int pageSize, QueryWrapper query);
    R info(int stuId);

    R verifyLogin(String username, String password);

}
