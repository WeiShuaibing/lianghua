package com.bishe.lianghua.controller;

import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.entity.Student;
import com.bishe.lianghua.entity.Teacher;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody Student student) {
        student.setCreateDate(new Date());
        student.setUpdateDate(new Date());
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
     * 获取所有
     */
    @GetMapping("/getAll")
    public R getAll() {
        return new R();
    }


}
