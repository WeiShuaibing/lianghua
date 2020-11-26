package com.bishe.lianghua.entity;

/**
 * 班级和课程的关系类
 */
public class Classcourserel {

    private int classCourseRel;
    private int classId;
    private int courseId;

    public int getClassCourseRel() {
        return classCourseRel;
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

    @Override
    public String toString() {
        return "Classcourserel{" +
                "classCourseRel=" + classCourseRel +
                ", classId=" + classId +
                ", courseId=" + courseId +
                '}';
    }
}
