package com.bishe.lianghua.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.lianghua.entity.Admin;
import com.bishe.lianghua.entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends BaseMapper<Message> {
}
