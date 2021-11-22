package com.example.geniusmarket.utils;

import com.example.geniusmarket.pojo.User;

import java.util.Objects;

public class Data <T extends Cloneable>{
    private T data;
    private User user;
    String type;

    public Data(T data, User user) {
        this.data = data;
        this.user = user;
        type = data.getClass().getCanonicalName().replaceAll("com.example.geniusmarket.pojo.","");
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                ", user=" + user +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data<?> data1 = (Data<?>) o;
        return Objects.equals(data, data1.data) && Objects.equals(user, data1.user) && Objects.equals(type, data1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, user, type);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
