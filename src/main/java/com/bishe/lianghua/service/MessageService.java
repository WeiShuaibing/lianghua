package com.bishe.lianghua.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.lianghua.entity.Message;

public interface MessageService extends IService<Message> {

    int teaReply(int id, String reply);
}
