package com.bishe.lianghua.entity;

import java.util.List;
import java.util.Map;

/**
 * 班级  和 课程关系返回前端实体类，携带班级和课程完整信息
 */

public class ClasscourserelReturn {

    private int classCourseRel;
    private int classId;
    private int courseId;
    private String scoringIds;

    private Myclass myclass;
    private Course course;
    private List<Map<String, Object>> scorings;

    public int getClassCourseRel() {
        return classCourseRel;
    }


    public List<Map<String, Object>> getScorings() {
        return scorings;
    }

    public void setScorings(List<Map<String, Object>> scorings) {
        this.scorings = scorings;
    }

    public void setClassCourseRel(int classCourseRel) {
        this.classCourseRel = classCourseRel;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getScoringIds() {
        return scoringIds;
    }

    public void setScoringIds(String scoringIds) {
        this.scoringIds = scoringIds;
    }

    public Myclass getMyclass() {
        return myclass;
    }

    public void setMyclass(Myclass myclass) {
        this.myclass = myclass;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ClasscourserelReturn{" +
                "classCourseRel=" + classCourseRel +
                ", classId=" + classId +
                ", courseId=" + courseId +
                ", scoringIds='" + scoringIds + '\'' +
                ", myclass=" + myclass +
                ", course=" + course +
                '}';
    }
}
