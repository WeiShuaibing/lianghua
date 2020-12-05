package com.bishe.lianghua.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.lianghua.entity.Admin;
import com.bishe.lianghua.entity.Message;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends BaseMapper<Message> {

    @Update("update message set reply = #{reply} where message_id = #{id}")
    public int teaReply(@Param("id") int id,@Param("reply") String reply);

}
