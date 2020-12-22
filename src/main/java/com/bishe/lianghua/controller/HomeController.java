package com.bishe.lianghua.controller;

import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.service.ClassService;
import com.bishe.lianghua.service.CourseService;
import com.bishe.lianghua.service.StudentService;
import com.bishe.lianghua.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

 /**
 * 统计首页数据总览
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherServicep;
    @Autowired
    private ClassService classService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/getBaseData")
    public R baseData() {
        HashMap<String, Integer> res = new HashMap<>();
        res.put("studentTotal", studentService.count());
        res.put("teacherTotal", teacherServicep.count());
        res.put("classTotal", classService.count());
        res.put("courseTotal", courseService.count());
        return new R(res);
    }
}
