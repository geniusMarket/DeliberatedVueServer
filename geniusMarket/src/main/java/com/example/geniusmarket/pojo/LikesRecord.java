package com.example.geniusmarket.pojo;

import java.util.Objects;

public class LikesRecord {
    public static final int ANNOTATION = 1;
    public static final int ANNOTATION_REPLY = 2;
    public static final int ANSWER = 3;
    public static final int ANSWER_REPLY = 4;
    public static final int ARTICLE = 5;
    public static final int ARTICLE_REPLY = 6;
    public static final int QUESTION = 7;
    private String openId;
    private int tid;
    private int type;

    public LikesRecord(String openID, int tid, int type) {
        this.openId = openID;
        this.tid = tid;
        this.type = type;
    }
    public  LikesRecord(){}

    public String getOpenID() {
        return openId;
    }

    public void setOpenID(String openID) {
        this.openId = openID;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LikesRecord{" +
                "openID='" + openId + '\'' +
                ", tid=" + tid +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikesRecord)) return false;
        LikesRecord that = (LikesRecord) o;
        return tid == that.tid && type == that.type && Objects.equals(openId, that.openId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openId, tid, type);
    }
}
