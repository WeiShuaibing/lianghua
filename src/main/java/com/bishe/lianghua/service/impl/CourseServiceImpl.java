package com.bishe.lianghua.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.lianghua.dao.ClassCourseRelDao;
import com.bishe.lianghua.dao.CourseDao;
import com.bishe.lianghua.dao.ScoringDao;
import com.bishe.lianghua.entity.Classcourserel;
import com.bishe.lianghua.entity.Course;
import com.bishe.lianghua.entity.Scoring;
import com.bishe.lianghua.service.CourseService;
import com.bishe.lianghua.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {

    @Autowired
    private ClassCourseRelDao classCourseRelDao;

    @Override
    public List<Course> getCourseOfClassId(int classId) {
        return classCourseRelDao.getCourseOfClassId(classId);
    }
}
