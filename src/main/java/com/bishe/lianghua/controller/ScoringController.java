package com.bishe.lianghua.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bishe.lianghua.entity.Admin;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.entity.Scoring;
import com.bishe.lianghua.service.AdminService;
import com.bishe.lianghua.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 计分项 控制层
 */

@RestController
@RequestMapping("/scoring")
public class ScoringController {

    @Autowired
    private ScoringService scoringService;

    /**
     * 保存新的计分项
     */
    @PostMapping("/save")
    public R save(@RequestBody Scoring scoring) {
        scoring.setCreateDate(new Date());
        scoring.setUpdateDate(new Date());
        boolean save = scoringService.saveOrUpdate(scoring);
        if (save) {
            return new R();
        } else {
            return new R(20001, "服务异常，保存失败！");
        }
    }

    /**
     * 根据id查询某个计分项的详细信息
     */
    @GetMapping("/getById")
    public R getById(int id){
        Scoring scoring = scoringService.getById(id);
        return new R(scoring);
    }

    /**
     * 根据id删除某计分项
     */
    @GetMapping("/deleteById")
    public R deleteById(int id) {
        boolean b = scoringService.removeById(id);
        if (b) {
            return new R("删除成功");
        } else {
            return new R(20001, "服务异常，删除失败！");
        }
    }

    /**
     * 获取所有的计分项信息，用于前端表格展示
     */
    @GetMapping("/getAll")
    public R getAll() {
        QueryWrapper<Scoring> query = Wrappers.query();
        List<Scoring> list = scoringService.list(query.orderByDesc("scoring_id"));
        return new R(list);
    }

}
