package com.bishe.lianghua.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.lianghua.entity.Classcourserel;
import com.bishe.lianghua.entity.Course;
import com.bishe.lianghua.entity.R;

import java.util.Map;

public interface ClassCourseRelService extends IService<Classcourserel> {


    Map getClassAndCourseInfoByRelId(int relId);

}
