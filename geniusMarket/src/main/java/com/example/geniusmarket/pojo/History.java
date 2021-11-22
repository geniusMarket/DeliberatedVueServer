package com.example.geniusmarket.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class History implements Cloneable{
    private String userId;
    private  int questionId;
    private Timestamp createTime;
    public History(){createTime = new Timestamp(new Date().getTime());}

    public History(String userId, int questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof History)) return false;
        History history = (History) o;
        return questionId == history.questionId && Objects.equals(userId, history.userId) && Objects.equals(createTime, history.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, questionId, createTime);
    }

    @Override
    public String toString() {
        return "History{" +
                "userId='" + userId + '\'' +
                ", questionId=" + questionId +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        History history = null;
        try
        {
            history = (History) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return  history;
    }
}
