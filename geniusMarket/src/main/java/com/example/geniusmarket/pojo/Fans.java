package com.example.geniusmarket.pojo;

import java.util.Objects;

public class Fans implements Cloneable{
    private String fanId;
    private String attentionId;

    public Fans(String fanId, String attentionId) {
        this.fanId = fanId;
        this.attentionId = attentionId;
    }
    public Fans(){}

    public String getFanId() {
        return fanId;
    }

    public void setFanId(String fanId) {
        this.fanId = fanId;
    }

    public String getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fans)) return false;
        Fans fans = (Fans) o;
        return Objects.equals(fanId, fans.fanId) && Objects.equals(attentionId, fans.attentionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fanId, attentionId);
    }

    @Override
    public String toString() {
        return "Fans{" +
                "fanId='" + fanId + '\'' +
                ", attentionId='" + attentionId + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Fans fans = null;
        try
        {
           fans = (Fans) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return  fans;

    }
}
