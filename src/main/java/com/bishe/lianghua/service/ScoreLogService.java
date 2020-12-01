package com.bishe.lianghua.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.lianghua.entity.Score;
import com.bishe.lianghua.entity.ScoreLog;

public interface ScoreLogService extends IService<ScoreLog> {

    boolean addLog(ScoreLog scoreLog);

}
