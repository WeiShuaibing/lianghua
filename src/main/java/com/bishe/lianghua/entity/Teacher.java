package com.bishe.lianghua.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 老师实体类
 */
public class Teacher {
    @TableId(type = IdType.AUTO)
    private int teaId;
    private String classCourseRel;

    @TableField(exist = false)
    private String classCourseName;
    private String name;
    private String phone;
    private String password;
    private String remark;

    @Override
    public String toString() {
        return "Teacher{" +
                "teaId=" + teaId +
                ", classCourseRel=" + classCourseRel +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getClassCourseName() {
        return classCourseName;
    }

    public void setClassCourseName(String classCourseName) {
        this.classCourseName = classCourseName;
    }

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public String getClassCourseRel() {
        return classCourseRel;
    }

    public void setClassCourseRel(String classCourseRel) {
        this.classCourseRel = classCourseRel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
