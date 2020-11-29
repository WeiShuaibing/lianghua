package com.bishe.lianghua.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.lianghua.entity.Student;
import com.bishe.lianghua.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends BaseMapper<Student> {
}
