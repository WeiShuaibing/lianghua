package com.bishe.lianghua.controller;

import com.alibaba.fastjson.JSON;
import com.bishe.lianghua.entity.Classcourserel;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.service.ClassCourseRelService;
import com.bishe.lianghua.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/classcourserel")
public class ClasscourserelController {

    @Autowired
    private ClassCourseRelService classCourseRelService;
    @Autowired
    private ScoringService scoringService;
    /**
     * 更新某课程和班级关系的计分项属性
     */
    @PostMapping("/update")
    public R update(@RequestBody Classcourserel classcourserel) {
        boolean b = classCourseRelService.updateById(classcourserel);
        if (b) {
            return new R();
        } else {
            return new R(20001, "服务异常，更新失败！");
        }
    }

    @GetMapping("/getScoringInfoById")
    public R getScoringInfoById(int id) {
        Classcourserel byId = classCourseRelService.getById(id);
        Map map = JSON.parseObject(byId.getScoringIds(), Map.class);
        if (map == null) {
            return new R();
        } else {
            ArrayList<Map<String, Object>> list = new ArrayList<>();
            for (Object key : map.keySet()) {
                HashMap<String, Object> item = new HashMap<>();
                item.put("scoringId", Integer.valueOf(key.toString()));
                item.put("scoringScore", map.get(key));
                item.put("title", scoringService.getById(Integer.valueOf(key.toString())).getTitle());

                list.add(item);
            }
            return new R(list);
        }

    }

}
