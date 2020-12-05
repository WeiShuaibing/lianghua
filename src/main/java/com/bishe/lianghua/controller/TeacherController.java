package com.bishe.lianghua.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bishe.lianghua.entity.*;
import com.bishe.lianghua.service.CourseService;
import com.bishe.lianghua.service.MessageService;
import com.bishe.lianghua.service.StudentService;
import com.bishe.lianghua.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 教师控制层
 */

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private StudentService studentService;

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody Teacher teacher) {
        boolean b = teacherService.saveOrUpdate(teacher);
        if (b) {
            return new R();
        } else {
            return new R(20001, "服务异常");
        }
    }

    /**
     * 根据id查询某
     */
    @GetMapping("/getById")
    public R getById(int id){
        return new R(teacherService.getById(id));
    }

    /**
     * 根据id删除
     */
    @GetMapping("/deleteById")
    public R deleteById(int id) {
        teacherService.removeById(id);
        return new R();
    }

    /**
     * 分页获取
     */
    @GetMapping("/getPage")
    public R getPage(int pageNum, int pageSize, String matchStr) {
        if (matchStr == null || matchStr.equals("")) {
            return teacherService.getPage(pageNum, pageSize);
        } else {
            return teacherService.getPage(pageNum, pageSize, matchStr);
        }
    }

    /**
     * 查询我所教的课程信息
     */
    @GetMapping("/getMyCourseAllInfo")
    public R getMyCourseAllInfo(@RequestHeader String LHToken) {
        String s = LHToken.split("_")[1];
        R myCourse = teacherService.getMyCourseAllinfo(Integer.valueOf(s));
        return myCourse;
    }

    /**
     * 获取我的留言
     * @return
     */
    @GetMapping("/getMyMessage")
    public R getMyMessage(@RequestHeader String LHToken) {

        int teaId = Integer.valueOf(LHToken.split("_")[1]);
        QueryWrapper<Message> query = Wrappers.<Message>query();
        query.eq("tea_id", teaId).orderByDesc("message_id");
        List<Message> list = messageService.list(query);
        for (Message message : list) {
            Student byId = studentService.getById(message.getStuId());
            message.setStuName(byId == null ? "未知" : byId.getName());
        }
        return new R(list);
    }

}
