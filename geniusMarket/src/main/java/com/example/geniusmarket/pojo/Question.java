package com.example.geniusmarket.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Question implements Cloneable{
    public final static int EXAMINING = 0; //审核中
    public final static int EXAMINE_SUCCESS = 1;//审核成功
    public final static int EXAMINE_FAILED = 2;//审核失败
    private  int questionId;
    private String asker;
    private String title;
    private String detail;
    private int codeId;
    private int views=0;
    private int likes=0;
    private int reward=0;
    private Timestamp createTime;
    private int status = EXAMINE_SUCCESS;
    public Question()
    {
        createTime = new Timestamp(new Date().getTime());
    }

    public Question(String asker, String title, String detail, int codeId, int reward) {
        this.asker = asker;
        this.title = title;
        this.detail = detail;
        this.codeId = codeId;
        this.reward = reward;
        createTime = new Timestamp(new Date().getTime());
    }

    public Question(String asker, String title, String detail, int reward) {
        this.asker = asker;
        this.title = title;
        this.detail = detail;
        this.reward = reward;
        createTime = new Timestamp(new Date().getTime());
    }

    public Question(int questionId, String asker, String title, String detail, int codeId, int views, int likes, int reward, Timestamp createTime, int status) {
        this.questionId = questionId;
        this.asker = asker;
        this.title = title;
        this.detail = detail;
        this.codeId = codeId;
        this.views = views;
        this.likes = likes;
        this.reward = reward;
        this.createTime = createTime;
        this.status = status;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return questionId == question.questionId && codeId == question.codeId && views == question.views && likes == question.likes && status == question.status && Objects.equals(asker, question.asker) && Objects.equals(title, question.title) && Objects.equals(detail, question.detail) && Objects.equals(createTime, question.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, asker, title, detail, codeId, views, likes, createTime, status);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", asker='" + asker + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", codeId=" + codeId +
                ", views=" + views +
                ", likes=" + likes +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Question question = null;
        try {
            question = (Question) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return question;
    }
}
