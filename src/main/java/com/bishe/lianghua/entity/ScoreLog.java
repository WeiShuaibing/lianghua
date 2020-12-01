package com.bishe.lianghua.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @Description TODO
 * @Author WeiShuaibing
 * @Date 20/12/1/0001 10:43
 * @Version 1.0
 */
@TableName("scorelog")
public class ScoreLog {

    @TableId(type = IdType.AUTO)
    private int id;
    private int stuId; // 学生id

    @TableField(exist = false)
    private String stuName;
    private int classCourseRelId; // 班级课程id
    @TableField(exist = false)
    private String classNameAndCourseName;
    private String score; // 用户操作的时候的成绩信息
    @TableField(exist = false)
    private ArrayList<Map<String, Object>> scoreList;
    private String operator;
    private String behavior; // 操作行为

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    public ScoreLog() {
    }

    public ScoreLog(int stuId, int classCourseRelId, String score, String operator, String behavior) {
        this.stuId = stuId;
        this.classCourseRelId = classCourseRelId;
        this.score = score;
        this.operator = operator;
        this.behavior = behavior;
    }

    public ScoreLog(int stuId, int classCourseRelId, String score, String operator, String behavior, Date createDate) {
        this.stuId = stuId;
        this.classCourseRelId = classCourseRelId;
        this.score = score;
        this.operator = operator;
        this.behavior = behavior;
        this.createDate = createDate;
    }

    public String getClassNameAndCourseName() {
        return classNameAndCourseName;
    }

    public void setClassNameAndCourseName(String classNameAndCourseName) {
        this.classNameAndCourseName = classNameAndCourseName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public ArrayList<Map<String, Object>> getScoreList() {
        return scoreList;
    }

    public void setScoreList(ArrayList<Map<String, Object>> scoreList) {
        this.scoreList = scoreList;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getClassCourseRelId() {
        return classCourseRelId;
    }

    public void setClassCourseRelId(int classCourseRelId) {
        this.classCourseRelId = classCourseRelId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
