package com.bishe.lianghua.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bishe.lianghua.entity.*;
import com.bishe.lianghua.service.ScoreLogService;
import com.bishe.lianghua.service.ScoreService;
import com.bishe.lianghua.service.StudentService;
import com.bishe.lianghua.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreLogService scoreLogService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 获取某课程的所有学生成绩信息
     */
    @GetMapping("/getPageOfScore")
    public R getPage(int classId, int courseId, int classCourseRel, int pageNum, int pageSize) {

        /**
         * 思路：
         * 1.根据班级id获取本班级的所有同学
         * 2.根据score成绩表中的数据给刚才的获取到的所有学生信息进行加工
         */
        QueryWrapper<Student> query = Wrappers.<Student>query();
        query.eq("class_id", classId).orderByDesc("stu_id");
        Page<Student> page = studentService.getPage(pageNum, pageSize, query);

        ArrayList<ScoreReturn> scoreReturnList = new ArrayList<>(); // 所有封装好的成绩数据
        for (Student stu : page.getRecords()) {
            Score scoreOfStu = scoreService.getOne(Wrappers.<Score>query().eq("stu_id", stu.getStuId()).eq("class_course_rel_id", classCourseRel)); // 原始计分项数据
            Float finalScore = 0f;

            if (scoreOfStu != null) {
            // 计算最终得分数据
            ArrayList scoringList = JSON.parseObject(scoreOfStu.getScore(), ArrayList.class);
                for (Object item : scoringList) {
                    Map scoringItemMap = JSON.parseObject(item.toString(), Map.class);
                    Float scoringScore = Float.valueOf(scoringItemMap.get("scoringScore").toString());
                    Float score = Float.valueOf(scoringItemMap.get("score").toString());
                    finalScore += score * scoringScore;
                }
            }

            scoreReturnList.add(new ScoreReturn(scoreOfStu,finalScore,  stu));
        }
        Page<ScoreReturn> resPage = new Page<>(pageNum, pageSize);
        resPage.setTotal(page.getTotal());
        resPage.setRecords(scoreReturnList);
        return new R(resPage);
    }


    @PostMapping("/save")
    public R save(@RequestBody Score score, @RequestHeader String LHToken) {

        // 构造用户的操作日志
        String behavior = "";
        if (score.getId() != 0) {
            behavior = "修改";
        } else {
            behavior = "添加";
        }
        ScoreLog scoreLog = new ScoreLog(score.getStuId(), score.getClassCourseRelId(), score.getScore(),
                teacherService.getById(Integer.valueOf(LHToken.split("_")[1])).getName(), behavior, new Date());
        scoreLogService.save(scoreLog); // 保存操作日志

        boolean b = scoreService.saveOrUpdate(score);
        if (b) {
            return new R();
        } else {
            return new R(20001, "服务异常，保存失败！");
        }
    }
}
