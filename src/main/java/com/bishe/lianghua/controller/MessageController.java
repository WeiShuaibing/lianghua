package com.bishe.lianghua.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bishe.lianghua.entity.Admin;
import com.bishe.lianghua.entity.Message;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.service.AdminService;
import com.bishe.lianghua.service.MessageService;
import com.bishe.lianghua.service.StudentService;
import com.bishe.lianghua.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 */

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/save")
    public R save(@RequestBody Message message, @RequestHeader String LHToken) {
        System.out.println(LHToken);
        message.setStuId(Integer.valueOf(LHToken.split("_")[1]));
        boolean save = messageService.save(message);
        if (save) {
            return new R();
        } else {
            return new R(20001, "服务异常，保存失败！");
        }
    }

    @GetMapping("/deleteById")
    public R deleteById(int id) {
        boolean b = messageService.removeById(id);
        if (b) {
            return new R();
        } else {
            return new R(20001, "服务异常，删除失败！");
        }
    }

    @GetMapping("getAll")
    public R getAll(@RequestHeader String LHToken) {
        QueryWrapper<Message> query = Wrappers.<Message>query();
        query.eq("stu_id", Integer.valueOf(LHToken.split("_")[1]));
        List<Message> list = messageService.list(query);
        for (Message message : list) {
            message.setTeaName(teacherService.getById(message.getTeaId()).getName());
        }
        return new R(list);
    }

}
