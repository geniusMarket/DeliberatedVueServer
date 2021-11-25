package com.example.geniusmarket.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class AnswerReply implements Cloneable{
    public final static int EXAMINING = 0; //审核中
    public final static int EXAMINE_SUCCESS = 1;//审核成功
    public final static int EXAMINE_FAILED = 2;//审核失败
    private int answerReplyId;
    private int answerId;
    private String replier;
    private String detail;
    private int likes=0;
    private int reward=0;
    private int status=EXAMINE_SUCCESS;
    private Timestamp createTime;
    public AnswerReply(int answerId, String replier, String detail, int reward) {
        this.answerId = answerId;
        this.replier = replier;
        this.detail = detail;
        this.reward = reward;
        this.createTime = new Timestamp(new Date().getTime());
    }
    public AnswerReply(){  this.createTime = new Timestamp(new Date().getTime());}

    public AnswerReply(int answerReplyId, int answerId, String replier, String detail, int likes, int reward, int status, Timestamp createTime) {
        this.answerReplyId = answerReplyId;
        this.answerId = answerId;
        this.replier = replier;
        this.detail = detail;
        this.likes = likes;
        this.reward = reward;
        this.status = status;
        this.createTime = createTime;
    }

    public int getAnswerReplyId() {
        return answerReplyId;
    }

    public void setAnswerReplyId(int answerReplyId) {
        this.answerReplyId = answerReplyId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerReply)) return false;
        AnswerReply that = (AnswerReply) o;
        return answerReplyId == that.answerReplyId && answerId == that.answerId && likes == that.likes && reward == that.reward && status == that.status && Objects.equals(replier, that.replier) && Objects.equals(detail, that.detail) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerReplyId, answerId, replier, detail, likes, reward, status, createTime);
    }

    @Override
    public String toString() {
        return "AnswerReply{" +
                "answerReplyId=" + answerReplyId +
                ", answerId=" + answerId +
                ", replier='" + replier + '\'' +
                ", detail='" + detail + '\'' +
                ", likes=" + likes +
                ", reward=" + reward +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AnnotationReply annotationReply = null;
        try
        {
            annotationReply = (AnnotationReply) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return  annotationReply;
    }
}
