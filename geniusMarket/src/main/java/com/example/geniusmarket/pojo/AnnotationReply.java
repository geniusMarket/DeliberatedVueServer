package com.example.geniusmarket.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class AnnotationReply implements Cloneable{
    public final static int EXAMINING = 0; //审核中
    public final static int EXAMINE_SUCCESS = 1;//审核成功
    public final static int EXAMINE_FAILED = 4;//审核失败
    public final static int DANGEROUS = 2;
    public final static int SAFE = 3;
    private  int replyId;
    private String userId;
    private  int annotationId;
    private String detail;
    private Timestamp createTime;
    private int likes=0;
    private int status = EXAMINE_SUCCESS;
    public AnnotationReply()
    {
        createTime = new Timestamp(new Date().getTime());
    }

    public AnnotationReply(String userId, int annotationId, String detail) {
        this.userId = userId;
        this.annotationId = annotationId;
        this.detail = detail;
        this.createTime = new Timestamp(new Date().getTime());
    }

    public AnnotationReply(int replyId, String userId, int annotationId, String detail, Timestamp createTime, int likes, int status) {
        this.replyId = replyId;
        this.userId = userId;
        this.annotationId = annotationId;
        this.detail = detail;
        this.createTime = createTime;
        this.likes = likes;
        this.status = status;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(int annotationId) {
        this.annotationId = annotationId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnnotationReply)) return false;
        AnnotationReply that = (AnnotationReply) o;
        return replyId == that.replyId && annotationId == that.annotationId && likes == that.likes && status == that.status && Objects.equals(userId, that.userId) && Objects.equals(detail, that.detail) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(replyId, userId, annotationId, detail, createTime, likes, status);
    }

    @Override
    public String toString() {
        return "AnnotationReply{" +
                "replyId=" + replyId +
                ", userId='" + userId + '\'' +
                ", annotationId=" + annotationId +
                ", detail='" + detail + '\'' +
                ", createTime=" + createTime +
                ", likes=" + likes +
                ", status=" + status +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AnnotationReply annotationReply= null;
        try
        {
            annotationReply =(AnnotationReply) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return annotationReply;
    }
}
