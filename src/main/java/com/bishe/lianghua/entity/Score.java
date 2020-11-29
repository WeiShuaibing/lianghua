package com.bishe.lianghua.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

    /**
     * 学生成绩表
     */
public class Score {

    @TableId(type = IdType.AUTO)
    private int id; // 自增id
    private Integer stuId; // 学生id
    private Integer classCourseRelId; // 班级课程关系id
    private String score;  // 成绩是不同的计分项的综合计分情况，使用json存储每一项的成绩

    public Score() {
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", classCourseRelId=" + classCourseRelId +
                ", score='" + score + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getClassCourseRelId() {
        return classCourseRelId;
    }

    public void setClassCourseRelId(Integer classCourseRelId) {
        this.classCourseRelId = classCourseRelId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
