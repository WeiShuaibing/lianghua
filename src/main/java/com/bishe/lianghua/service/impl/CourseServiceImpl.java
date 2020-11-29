package com.bishe.lianghua.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.lianghua.dao.ClassCourseRelDao;
import com.bishe.lianghua.dao.CourseDao;
import com.bishe.lianghua.dao.ScoringDao;
import com.bishe.lianghua.entity.*;
import com.bishe.lianghua.service.ClassService;
import com.bishe.lianghua.service.CourseService;
import com.bishe.lianghua.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {

    @Autowired
    private ClassCourseRelDao classCourseRelDao;
    @Autowired
    private ClassService classService;

    @Override
    public List<Course> getCourseOfClassId(int classId) {
        return classCourseRelDao.getCourseOfClassId(classId);
    }

    @Override
    public R getAllCourseWithFormat() {


        ArrayList<Map<String, Object>> resList = new ArrayList<>();
        List<Myclass> courseList = classService.list();

        for (Myclass myclass : courseList) {
            HashMap<String, Object> resMap = new HashMap<>();
            resMap.put("value", myclass.getClassId());
            resMap.put("label", myclass.getName());

            List<Map> coursesAndRel = getRelIdAndCourse(myclass.getClassId());
            ArrayList<Map<String, String>> courseItems = new ArrayList<>();
            for (Map c : coursesAndRel) {
                HashMap<String, String> courseItemMap = new HashMap<>();
                courseItemMap.put("value", String.valueOf(c.get("class_course_rel")));
                Course cou = (Course)c.get("course");
                courseItemMap.put("label", cou.getName());
                courseItems.add(courseItemMap);
            }
            resMap.put("children", courseItems);
            resList.add(resMap);
        }

        return new R(resList);
    }


    /**
     * 获取关系表的id和课程的基本信息
     */
    public List<Map> getRelIdAndCourse(int courseId) {
        ArrayList<Map> list = new ArrayList<>();

        List<Classcourserel> rels = classCourseRelDao.selectList(Wrappers.<Classcourserel>query().eq("class_id", courseId));
        for (Classcourserel rel : rels) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("class_course_rel", rel.getClassCourseRel());
            map.put("course", this.getById(rel.getCourseId()));
            list.add(map);
        }
        return list;
    }

}
