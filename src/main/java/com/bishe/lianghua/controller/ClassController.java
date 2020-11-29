package com.bishe.lianghua.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bishe.lianghua.entity.Course;
import com.bishe.lianghua.entity.Myclass;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.service.ClassService;
import com.bishe.lianghua.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 班级 控制层
 */

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody Myclass myclass) {
        return classService.saveInfo(myclass);
    }

    /**
     * 更新班级信息
     */
    @PostMapping("/update")
    public R update(@RequestBody Myclass myclass) {
        return classService.updateInfo(myclass);
    }

    /**
     * 根据id查询某
     */
    @GetMapping("/getById")
    public R getById(int id){
        Myclass course = classService.getById(id);
        return new R(course);
    }

    /**
     * 根据id删除
     */
    @GetMapping("/deleteById")
    public R deleteById(int id) {
        boolean b = classService.removeById(id);
        if (b) {
            return new R("删除成功");
        } else {
            return new R(20001, "服务异常，删除失败！");
        }
    }

    /**
     * 分页获取
     */
    @GetMapping("/getPage")
    public R getPage(int pageNum, int pageSize) {
        return classService.getPage(pageNum, pageSize);
    }

    /**
     * 获取所有的班级数据
     */
    @GetMapping("/getAll")
    public R getAll() {
        return new R(classService.list());
    }
}
