package com.bishe.lianghua.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.lianghua.entity.Course;
import com.bishe.lianghua.entity.Myclass;
import com.bishe.lianghua.entity.R;

public interface ClassService extends IService<Myclass> {

    public R saveInfo(Myclass myclass);
    public R updateInfo(Myclass myclass);
    public R getPage(int pageNum, int pageSize);
}
