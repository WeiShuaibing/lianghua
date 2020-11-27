package com.bishe.lianghua.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.lianghua.dao.CourseDao;
import com.bishe.lianghua.dao.MyClassDao;
import com.bishe.lianghua.entity.Classcourserel;
import com.bishe.lianghua.entity.Course;
import com.bishe.lianghua.entity.Myclass;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.service.ClassCourseRelService;
import com.bishe.lianghua.service.ClassService;
import com.bishe.lianghua.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 */
@Service
public class ClassServiceImpl extends ServiceImpl<MyClassDao, Myclass> implements ClassService {

    @Autowired
    private MyClassDao classDao;
    @Autowired
    private ClassCourseRelService classCourseRelService;

    @Override
    public R saveInfo(Myclass myclass) {
        classDao.saveAndReturnId(myclass); // 保存到数据库后，自增的主键会被赋值到 myclass对象上

        int classId = myclass.getClassId();
        ArrayList<Classcourserel> classcourserels = new ArrayList<>();
        for (String courseId : myclass.getCourse().split(",")) {
            classcourserels.add(new Classcourserel(classId, Integer.parseInt(courseId)));
        }
        classCourseRelService.saveBatch(classcourserels); // 将班级 和 相应的课程数据保存到 班级课程关系表中
        return new R();
    }

    @Override
    public R updateInfo(Myclass myclass) {
        classDao.updateById(myclass);

        ArrayList<Classcourserel> classcourserels = new ArrayList<>();
        for (String courseId : myclass.getCourse().split(",")) {
            classcourserels.add(new Classcourserel(myclass.getClassId(), Integer.parseInt(courseId)));
        }

        // 先删除本班级相关的课程，然后重新保存最新的课程数据
        QueryWrapper<Classcourserel> relQuery = Wrappers.query();
        classCourseRelService.remove(relQuery.eq("class_id",myclass.getClassId()));

        classCourseRelService.saveBatch(classcourserels); // 将班级 和 相应的课程数据保存到 班级课程关系表中
        return new R();
    }

    @Override
    public R getPage(int pageNum, int pageSize) {

        Page<Myclass> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Myclass> query = Wrappers.query();
        query.orderByDesc("class_id");
        Page<Myclass> myclassPage = classDao.selectPage(page, query);
        return new R(myclassPage);
    }
}
