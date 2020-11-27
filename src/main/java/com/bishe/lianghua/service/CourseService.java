package com.bishe.lianghua.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.lianghua.entity.Course;

import java.util.List;

public interface CourseService extends IService<Course> {

    public List<Course> getCourseOfClassId(int classId);

}
