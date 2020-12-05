package com.bishe.lianghua.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.lianghua.dao.AdminDao;
import com.bishe.lianghua.dao.MessageDao;
import com.bishe.lianghua.entity.Admin;
import com.bishe.lianghua.entity.Message;
import com.bishe.lianghua.entity.R;
import com.bishe.lianghua.service.AdminService;
import com.bishe.lianghua.service.MessageService;
import com.bishe.lianghua.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public int teaReply(int id, String reply) {
        return messageDao.teaReply(id, reply);
    }
}
