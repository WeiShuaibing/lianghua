package com.bishe.lianghua.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.lianghua.dao.AdminDao;
import com.bishe.lianghua.dao.ClassCourseRelDao;
import com.bishe.lianghua.dao.TeacherDao;
import com.bishe.lianghua.entity.*;
import com.bishe.lianghua.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ClassCourseRelDao classCourseRelDao;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassService classService;
    @Autowired
    private ScoringService scoringService;
    @Autowired
    private StudentService studentService;

    @Override
    public R getPage(int pageNum, int pageSize) {
        Page<Teacher> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Teacher> query = Wrappers.query();
        query.orderByDesc("tea_id");
        Page<Teacher> studentPage = teacherDao.selectPage(page, query);
        List<Teacher> records = studentPage.getRecords();
        for (Teacher record : records) {
            StringBuilder sb = new StringBuilder();
            String rels = record.getClassCourseRel();
            for (String relId : rels.split(",")) {
                Map<String, String> classAndCourseMap = classCourseRelDao.queryClassNameAndCourseName(Integer.valueOf(relId));
                sb.append(classAndCourseMap.get("class_name")).append("/").append(classAndCourseMap.get("course_name")).append(" | ");
            }
            record.setClassCourseName(sb.substring(0, sb.length()-3));
        }
        return new R(studentPage);
    }

    @Override
    public R getPage(int pageNum, int pageSize, String matchStr) {
        Page<Teacher> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Teacher> query = Wrappers.query();
        query.orderByDesc("tea_id");
        query.like("name", matchStr).or().like("phone", matchStr).or().like("remark", matchStr).or().like("tea_id", matchStr);
        Page<Teacher> studentPage = teacherDao.selectPage(page, query);
        List<Teacher> records = studentPage.getRecords();

        for (Teacher record : records) {
            StringBuilder sb = new StringBuilder();
            String rels = record.getClassCourseRel();
            for (String relId : rels.split(",")) {
                Map<String, String> classAndCourseMap = classCourseRelDao.queryClassNameAndCourseName(Integer.valueOf(relId));
                sb.append(classAndCourseMap.get("class_name")).append("/").append(classAndCourseMap.get("course_name")).append(" | ");
            }
            record.setClassCourseName(sb.substring(0, sb.length()-3));
        }
        return new R(studentPage);
    }


    @Override
    public R verifyLogin(String username, String password) {
        QueryWrapper<Teacher> wrapper = Wrappers.<Teacher>query();
        wrapper.eq("phone", username);
        Teacher teacher = teacherDao.selectOne(wrapper);
        R r = new R();
        if (teacher == null) {
            // 去调用学生的登录接口
            return studentService.verifyLogin(username, password);
        } else if (!teacher.getPassword().equals(password)) {
            r.setCode(20001);
            r.setMsg("密码或者用户名错误");
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("token","teacher_" + teacher.getTeaId());
            map.put("roles", "teacher");
            map.put("username",teacher.getName());
            map.put("avatar", "https://s2.ax1x.com/2019/07/17/ZLWJSA.gif?imageView2/1/w/80/h/80");
            r.setData(map);
        }
        return r;
    }

    @Override
    public R info(int teaId) {
        Teacher teacher = this.getById(teaId);
        HashMap<String, Object> tea = new HashMap<>();
        tea.put("id", teacher.getTeaId());
        tea.put("username", teacher.getName());
        tea.put("roles", "teacher");
        tea.put("avatar", "https://s2.ax1x.com/2019/07/17/ZLWJSA.gif?imageView2/1/w/80/h/80");
        return new R(tea);
    }

    @Override
    public R getMyCourseAllinfo(int teaId) {
        Teacher tea = this.getById(teaId);
        String classCourseRel = tea.getClassCourseRel();
        String[] rels = classCourseRel.split(",");
        List<Classcourserel> relEntitys = classCourseRelDao.selectList(Wrappers.<Classcourserel>query().in("class_course_rel", rels));
        // 根据班级和课程的关系 查询出班级信息和课程信息
        ArrayList<ClasscourserelReturn> classCourseInfoList = new ArrayList<>();
        for (Classcourserel relEntity : relEntitys) {
            ClasscourserelReturn item = new ClasscourserelReturn();
            item.setClassCourseRel(relEntity.getClassCourseRel());
            item.setClassId(relEntity.getClassId());
            item.setCourseId(relEntity.getCourseId());
            item.setScoringIds(relEntity.getScoringIds());
            item.setCourse(courseService.getById(relEntity.getCourseId())); // 获取课程信息
            item.setMyclass(classService.getById(relEntity.getClassId())); // 获取班级信息

            // 获取评分项信息
            String scoringIds = relEntity.getScoringIds();
            if (scoringIds != null) {
                Map scroingMap = JSON.parseObject(scoringIds, Map.class);
                ArrayList<Map<String, Object>> scoringsList = new ArrayList<>();
                for (Object key : scroingMap.keySet()) {
                    Scoring byId = scoringService.getById(Integer.valueOf(key.toString()));
                    HashMap<String, Object> m = new HashMap<>();
                    m.put("title", byId.getTitle());
                    m.put("scoringId", byId.getScoringId());
                    m.put("num", scroingMap.get(key));
                    scoringsList.add(m);
                }
                item.setScorings(scoringsList);
            } else {
                item.setScorings(null);
            }

            classCourseInfoList.add(item);
        }

        return new R(classCourseInfoList);
    }
}





















