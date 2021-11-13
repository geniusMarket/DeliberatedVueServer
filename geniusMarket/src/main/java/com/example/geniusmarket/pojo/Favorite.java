package com.example.geniusmarket.pojo;

import java.util.Objects;

public class Favorite {
    private String userId;
    private int questionId;
    Favorite(){}

    public Favorite(String userId, int questionId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favorite)) return false;
        Favorite favorite = (Favorite) o;
        return questionId == favorite.questionId && Objects.equals(userId, favorite.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, questionId);
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "userId='" + userId + '\'' +
                ", questionId=" + questionId +
                '}';
    }
}
