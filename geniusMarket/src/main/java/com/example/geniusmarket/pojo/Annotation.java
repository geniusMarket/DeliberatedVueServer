package com.example.geniusmarket.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Annotation implements Cloneable{
    public final static int EXAMINING = 0; //审核中
    public final static int EXAMINE_SUCCESS = 1;//审核成功
    public final static int EXAMINE_FAILED = 4;//审核失败
    public final static int DANGEROUS = 2;
    public final static int SAFE = 3;
    private int annotationId;
    private String filePath;
    private String moduleName;
    private String detail;
    private Timestamp createTime;
    private String userId;
    private int likes = 0;
    private int status = EXAMINE_SUCCESS;
    public Annotation(){
        createTime = new Timestamp(new Date().getTime());
    }

    public Annotation(int annotationId, String filePath, String moduleName, String detail,String userId) {
        this.annotationId = annotationId;
        this.filePath = filePath;
        this.moduleName = moduleName;
        this.detail = detail;
        this.createTime = new Timestamp(new Date().getTime());
        this.userId = userId;
    }

    public Annotation(String filePath, String moduleName, String detail, String userId) {
        this.filePath = filePath;
        this.moduleName = moduleName;
        this.detail = detail;
        this.createTime = new Timestamp(new Date().getTime());
        this.userId = userId;
    }

    public Annotation(int annotationId, String filePath, String moduleName, String detail, Timestamp createTime, String userId, int likes, int status) {
        this.annotationId = annotationId;
        this.filePath = filePath;
        this.moduleName = moduleName;
        this.detail = detail;
        this.createTime = createTime;
        this.userId = userId;
        this.likes = likes;
        this.status = status;
    }

    public int getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(int annotationId) {
        this.annotationId = annotationId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        if (!(o instanceof Annotation)) return false;
        Annotation that = (Annotation) o;
        return annotationId == that.annotationId && likes == that.likes && status == that.status && Objects.equals(filePath, that.filePath) && Objects.equals(moduleName, that.moduleName) && Objects.equals(detail, that.detail) && Objects.equals(createTime, that.createTime) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annotationId, filePath, moduleName, detail, createTime, userId, likes, status);
    }

    @Override
    public String toString() {
        return "Annotation{" +
                "annotationId=" + annotationId +
                ", filePath='" + filePath + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", detail='" + detail + '\'' +
                ", createTime=" + createTime +
                ", userId='" + userId + '\'' +
                ", likes=" + likes +
                ", status=" + status +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Annotation annotation = null;
        try {
            annotation =(Annotation) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();

        }
        return annotation;
    }
}
