package com.bishe.lianghua.entity;

/**
 * 成绩 前后端交互实体类
 */
public class ScoreReturn {

    private Score score;       // 不同计分项的成绩原始数据
    private Float finalScore; // 计算过后的最终成绩
    private Student student;

    public ScoreReturn() {
    }

    public ScoreReturn(Score score, Student student) {
        this.score = score;
        this.student = student;
    }

    public ScoreReturn(Score score, Float finalScore, Student student) {
        this.score = score;
        this.finalScore = finalScore;
        this.student = student;
    }

    public Float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Float finalScore) {
        this.finalScore = finalScore;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
