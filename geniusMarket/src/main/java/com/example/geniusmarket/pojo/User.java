package com.example.geniusmarket.pojo;

import java.util.Objects;

public class User {
    private String openId;
    private String userInfo;
    private int score = 0;
    private int fans = 0;
    private int attention = 0;

    public User()
    {
    }
    public User(String openId, String userInfo, int score, int fans, int attention) {
        this.openId = openId;
        this.userInfo = userInfo;
        this.score = score;
        this.fans = fans;
        this.attention = attention;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getAttention() {
        return attention;
    }

    public void setAttention(int attention) {
        this.attention = attention;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return score == user.score && fans == user.fans && attention == user.attention && openId.equals(user.openId) && userInfo.equals(user.userInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openId, userInfo, score, fans, attention);
    }

    @Override
    public String toString() {
        return "User{" +
                "openId='" + openId + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", score=" + score +
                ", fans=" + fans +
                ", attention=" + attention +
                '}';
    }
}
