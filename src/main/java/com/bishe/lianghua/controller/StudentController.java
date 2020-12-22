package com.bishe.lianghua.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bishe.lianghua.entity.*;
import com.bishe.lianghua.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 教师控制层
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassCourseRelService classCourseRelService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ScoreService scoreService;

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody Student student) {
        student.setCreateDate(new Date());
        student.setUpdateDate(new Date());
        if (student.getStuId() != 0) {
            student.setPassword(null);
        }
        boolean b = studentService.saveOrUpdate(student);
        if (b) {
            return new R();
        } else  {
            return new R(20001, "服务异常，保存失败！");
        }
    }

    /**
     * 批量导入
     */
    @PostMapping("/saveBatch")
    public R saveBatch(@RequestBody List<Student> list) {
        list.forEach(a-> {
            a.setCreateDate(new Date());
            a.setUpdateDate(new Date());
        });
        boolean b = studentService.saveBatch(list);
        if (b) {
            return new R();
        } else  {
            return new R(20001, "服务异常，保存失败！");
        }
    }

    /**
     * 根据id查询某
     */
    @GetMapping("/getById")
    public R getById(int id){
        return new R();
    }

    /**
     * 根据id删除
     */
    @GetMapping("/deleteById")
    public R deleteById(int id) {
        boolean b = studentService.removeById(id);
        if (b) {
            return new R();
        } else  {
            return new R(20001, "服务异常，删除失败！");
        }
    }

    /**
     * 分页获取
     */
    @GetMapping("/getPage")
    public R getPage(int pageNum, int pageSize, String matchStr) {

        if (matchStr == null || matchStr.equals("")) {
            return new R(studentService.getPage(pageNum, pageSize));
        } else {
            return new R(studentService.getPage(pageNum, pageSize, matchStr));
        }
    }

    /**
     * 根据Token获取我的所有课程以及成绩信息
     */
    @GetMapping("/getMyScore")
    public R getMyScore(@RequestHeader String LHToken) {

        String stuId = LHToken.split("_")[1];
        Student student = studentService.getById(stuId);
        QueryWrapper<Classcourserel> query = Wrappers.<Classcourserel>query();
        query.eq("class_id", student.getClassId());
        List<Classcourserel> rels = classCourseRelService.list(query);

        ArrayList<Map<String, Object>> res = new ArrayList<>();
        for (Classcourserel rel : rels) {
            Course course = courseService.getById(rel.getCourseId());
            Score score = scoreService.getOne(Wrappers.<Score>query().eq("stu_id", stuId).eq("class_course_rel_id", rel.getClassCourseRel()));
            ArrayList scoreList = null;
            if (score != null) {
                scoreList = JSON.parseObject(score.getScore(), ArrayList.class);
            }

            ArrayList<Map<String, Object>> setList = new ArrayList<>();
            if (scoreList != null) {
                for (Object item : scoreList) {
                    Map scoreItemMap = JSON.parseObject(item.toString(), Map.class);
                    setList.add(scoreItemMap);
                }
                score.setScoreList(setList);
            }

            HashMap<String, Object> resMap = new HashMap<>();
            resMap.put("student", student);
            resMap.put("course", course);
            resMap.put("score", score);
            res.add(resMap);
        }

        return new R(res);
    }

    /**
     * 查询自己课程的所属老师
     */
    @GetMapping("/getAllMyTeacher")
    public R getAllMyTeacher(@RequestHeader String LHToken) {

        int classId = studentService.getById(Integer.valueOf(LHToken.split("_")[1])).getClassId();
        QueryWrapper<Classcourserel> query = Wrappers.<Classcourserel>query();
        query.eq("class_id", classId);
        List<Classcourserel> relList = classCourseRelService.list(query);
        System.out.println(relList);
        ArrayList<Integer> relIds = new ArrayList<>();
        for (Classcourserel rel : relList) {
            relIds.add(rel.getClassCourseRel());
        }
        System.out.println(relIds);
        List<Teacher> allTea = teacherService.list();
        ArrayList<Teacher> resultTeacher = new ArrayList<>();
        for (Teacher teacher : allTea) {
            for (String item : teacher.getClassCourseRel().split(",")) {
                if (relIds.contains(Integer.valueOf(item))) {
                    resultTeacher.add(teacher);
                    break;
                }
            }
        }

        return new R(resultTeacher);

    }

}
