package com.bishe.lianghua.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.lianghua.entity.Classcourserel;
import com.bishe.lianghua.entity.Course;
import com.bishe.lianghua.entity.Myclass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassCourseRelDao extends BaseMapper<Classcourserel> {


    @Select("SELECT * FROM course WHERE course_id IN (SELECT course_id from classcourserel WHERE class_id = #{classId})")
    public List<Course> getCourseOfClassId(int classId);

}
