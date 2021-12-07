package com.example.geniusmarket.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Answer implements Cloneable {
    public final static int EXAMINING = 0; //审核中
    public final static int EXAMINE_SUCCESS = 1;//审核成功
    public final static int EXAMINE_FAILED = 4;//审核失败
    public final static int DANGEROUS = 2;
    public final static int SAFE = 3;
    private int answerId;
    private String answerer;
    private int questionId;
    private String detail;
    private Timestamp createTime;
    private int likes =0;
    private int reward = 0;
    private int status = EXAMINE_SUCCESS;

    public Answer()
    {
        createTime = new Timestamp(new Date().getTime());
    }

    public Answer(String answerer, int questionId, String detail, int reward) {
        this.answerer = answerer;
        this.questionId = questionId;
        this.detail = detail;
        this.reward = reward;
        this.createTime = new Timestamp(new Date().getTime());
    }

    public Answer(int answerId, String answerer, int questionId, String detail, Timestamp createTime, int likes, int reward, int status) {
        this.answerId = answerId;
        this.answerer = answerer;
        this.questionId = questionId;
        this.detail = detail;
        this.createTime = createTime;
        this.likes = likes;
        this.reward = reward;
        this.status = status;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerer() {
        return answerer;
    }

    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return answerId == answer.answerId && questionId == answer.questionId && likes == answer.likes && reward == answer.reward && status == answer.status && Objects.equals(answerer, answer.answerer) && Objects.equals(detail, answer.detail) && Objects.equals(createTime, answer.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, answerer, questionId, detail, createTime, likes, reward, status);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", answerer='" + answerer + '\'' +
                ", questionId=" + questionId +
                ", detail='" + detail + '\'' +
                ", createTime=" + createTime +
                ", likes=" + likes +
                ", reward=" + reward +
                ", status=" + status +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Answer answer = null;
        try
        {
            answer =(Answer) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return answer;
    }
}
