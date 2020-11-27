package com.bishe.lianghua.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bishe.lianghua.entity.Course;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.entity.Scoring;
import com.bishe.lianghua.service.CourseService;
import com.bishe.lianghua.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 课程 控制层
 */

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody Course course) {
        course.setCreateDate(new Date());
        course.setUpdateDate(new Date());
        boolean save = courseService.saveOrUpdate(course);
        if (save) {
            return new R();
        } else {
            return new R(20001, "服务异常，保存失败！");
        }
    }

    /**
     * 根据id查询某
     */
    @GetMapping("/getById")
    public R getById(int id){
        Course course = courseService.getById(id);
        return new R(course);
    }

    /**
     * 根据id删除
     */
    @GetMapping("/deleteById")
    public R deleteById(int id) {
        boolean b = courseService.removeById(id);
        if (b) {
            return new R("删除成功");
        } else {
            return new R(20001, "服务异常，删除失败！");
        }
    }

    /**
     * 获取所有
     */
    @GetMapping("/getAll")
    public R getAll() {
        QueryWrapper<Course> query = Wrappers.query();
        List<Course> list = courseService.list(query.orderByDesc("course_id"));
        return new R(list);
    }

    /**
     * 根据班级id查询所拥有的的课程
     */
    @GetMapping("/getCourseOfClassId")
    public R getCourseOfClassId(int id) {
        List<Course> list = courseService.getCourseOfClassId(id);
        return new R(list);
    }

}
