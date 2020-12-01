package com.bishe.lianghua.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.lianghua.dao.ScoreLogDao;
import com.bishe.lianghua.entity.ScoreLog;
import com.bishe.lianghua.service.ScoreLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class ScoreLogServiceImpl extends ServiceImpl<ScoreLogDao, ScoreLog> implements ScoreLogService {

    @Autowired
    private ScoreLogDao scoreLogDao;

    @Override
    public boolean addLog(ScoreLog scoreLog) {
        int effectRow = scoreLogDao.insert(scoreLog);
        return effectRow == 1;
    }
}
