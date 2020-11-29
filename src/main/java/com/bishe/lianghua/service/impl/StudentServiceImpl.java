package com.bishe.lianghua.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.lianghua.dao.StudentDao;
import com.bishe.lianghua.dao.TeacherDao;
import com.bishe.lianghua.entity.Myclass;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.entity.Student;
import com.bishe.lianghua.entity.Teacher;
import com.bishe.lianghua.service.ClassService;
import com.bishe.lianghua.service.StudentService;
import com.bishe.lianghua.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClassService classService;

    @Override
    public Page<Student> getPage(int pageNum, int pageSize) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> query = Wrappers.query();
        query.orderByDesc("stu_id");
        Page<Student> studentPage = studentDao.selectPage(page, query);
        List<Student> records = studentPage.getRecords();
        for (Student record : records) {
            record.setClassName(classService.getById(record.getClassId()).getName());
        }
        return studentPage;
    }

    @Override
    public Page<Student> getPage(int pageNum, int pageSize, String matchStr) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> query = Wrappers.query();
        query.orderByDesc("stu_id");
        query.like("name", matchStr).or()
                .like("age", matchStr).or()
                .like("sex", matchStr).or()
                .like("remark", matchStr);
        Page<Student> studentPage = studentDao.selectPage(page, query);
        List<Student> records = studentPage.getRecords();
        for (Student record : records) {
            record.setClassName(classService.getById(record.getClassId()).getName());
        }
        return studentPage;
    }


    @Override
    public Page<Student> getPage(int pageNum, int pageSize, QueryWrapper query) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        Page<Student> studentPage = studentDao.selectPage(page, query);
        List<Student> records = studentPage.getRecords();
        for (Student record : records) {
            record.setClassName(classService.getById(record.getClassId()).getName());
        }
        return studentPage;
    }

    @Override
    public R info(int stuId) {
        Student student = this.getById(stuId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", student.getStuId());
        map.put("username", student.getName());
        map.put("roles", "student");
        map.put("avatar", "https://s2.ax1x.com/2019/07/17/ZLWJSA.gif?imageView2/1/w/80/h/80");
        return new R(map);
    }
}
