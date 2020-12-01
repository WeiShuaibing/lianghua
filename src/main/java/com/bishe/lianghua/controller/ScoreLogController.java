package com.bishe.lianghua.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.entity.ScoreLog;
import com.bishe.lianghua.service.ClassCourseRelService;
import com.bishe.lianghua.service.ScoreLogService;
import com.bishe.lianghua.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author WeiShuaibing
 * @Date 20/12/1/0001 11:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/scoreLog")
public class ScoreLogController {
    @Autowired
    private ScoreLogService scoreLogService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassCourseRelService classCourseRelService;


    @GetMapping("/getAll")
    public R getAll(int stuId) {
        QueryWrapper<ScoreLog> query = Wrappers.<ScoreLog>query();
        query.eq("stu_id", stuId).orderByDesc("id");
        List<ScoreLog> logs = scoreLogService.list(query);
        String stuName = studentService.getById(stuId).getName();

        for (ScoreLog log : logs) {
            log.setStuName(stuName);
            log.setScoreList(JSON.parseObject(log.getScore(), ArrayList.class));
            Map m = classCourseRelService.getClassAndCourseInfoByRelId(log.getClassCourseRelId());
            log.setClassNameAndCourseName(m.get("class_name") + "/" + m.get("course_name"));
        }
        return new R(logs);
    }

}
