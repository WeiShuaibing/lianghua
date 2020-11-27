package com.bishe.lianghua.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.lianghua.entity.Course;
import com.bishe.lianghua.entity.Myclass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface MyClassDao extends BaseMapper<Myclass> {

    /**
     * useGeneratedKeys为true,用来设置返回主键id的值,
     * keyProperty 代表数据库记录主键字段
     * keyColumn 代表 java对象成员属性名
     */
    @Insert("insert into myclass(name, term, week, remark) values (#{name}, #{term}, #{week}, #{remark})")
    @Options(keyProperty = "class_id", useGeneratedKeys = true, keyColumn = "classId")
    public int saveAndReturnId(Myclass myclass);

}
