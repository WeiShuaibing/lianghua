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
import java.util.Map;

@Repository
public interface ClassCourseRelDao extends BaseMapper<Classcourserel> {


    @Select("SELECT * FROM course WHERE course_id IN (SELECT course_id from classcourserel WHERE class_id = #{classId})")
    public List<Course> getCourseOfClassId(int classId);


    /**
     * 根据rel id 获取班级名称 和 课程名称
     */
    @Select("SELECT myclass.name class_name, course.name course_name FROM classcourserel, myclass, course WHERE classcourserel.class_course_rel = #{relId} AND" +
            " myclass.class_id = classcourserel.class_id AND course.course_id  = classcourserel.course_id")
    public Map<String, String > queryClassNameAndCourseName(int relId);

}
