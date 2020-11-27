package com.bishe.lianghua.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.lianghua.dao.AdminDao;
import com.bishe.lianghua.dao.ScoringDao;
import com.bishe.lianghua.entity.Admin;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.entity.Scoring;
import com.bishe.lianghua.service.AdminService;
import com.bishe.lianghua.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 */
@Service
public class ScoringServiceImpl extends ServiceImpl<ScoringDao, Scoring> implements ScoringService {

    @Autowired
    private ScoringDao scoringDao;

}
