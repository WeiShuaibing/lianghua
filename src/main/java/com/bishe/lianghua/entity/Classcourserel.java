package com.bishe.lianghua.entity;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 班级和课程的关系类
 */
public class Classcourserel {
    @TableId
    private Integer classCourseRel;
    private Integer classId;
    private Integer courseId;
    private String scoringIds;

    public Classcourserel() {
    }

    public Classcourserel(int classId, int courseId) {
        this.classId = classId;
        this.courseId = courseId;
    }

    public Integer getClassCourseRel() {
        return classCourseRel;
    }

    public void setClassCourseRel(Integer classCourseRel) {
        this.classCourseRel = classCourseRel;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getScoringIds() {
        return scoringIds;
    }

    public void setScoringIds(String scoringIds) {
        this.scoringIds = scoringIds;
    }
}
