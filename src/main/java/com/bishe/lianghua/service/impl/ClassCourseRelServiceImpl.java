package com.bishe.lianghua.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.lianghua.dao.ClassCourseRelDao;
import com.bishe.lianghua.dao.CourseDao;
import com.bishe.lianghua.entity.Classcourserel;
import com.bishe.lianghua.entity.Course;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.service.ClassCourseRelService;
import com.bishe.lianghua.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 */
@Service
public class ClassCourseRelServiceImpl extends ServiceImpl<ClassCourseRelDao, Classcourserel> implements ClassCourseRelService {

    @Autowired
    private ClassCourseRelDao classCourseRelDao;

    @Override
    public Map getClassAndCourseInfoByRelId(int relId) {
        return classCourseRelDao.queryClassNameAndCourseName(relId);
    }
}
