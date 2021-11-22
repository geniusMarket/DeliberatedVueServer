package com.example.geniusmarket.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class ArticleReply implements Cloneable{
    public final static int EXAMINING = 0; //审核中
    public final static int EXAMINE_SUCCESS = 1;//审核成功
    public final static int EXAMINE_FAILED = 2;//审核失败
    private int articleReplyId;
    private int articleId;
    private String replier;
    private String detail;
    private Timestamp createTime;
    private int views=0;
    private int likes=0;
    private int status=EXAMINING;
    public ArticleReply()
    {
        this.createTime = new Timestamp(new Date().getTime());
    }

    public ArticleReply(int articleId, String replier, String detail) {
        this.articleId = articleId;
        this.replier = replier;
        this.detail = detail;
        this.createTime = new Timestamp(new Date().getTime());
    }

    public ArticleReply(int articleReplyId, int articleId, String replier, String detail, Timestamp createTime, int views, int likes, int status) {
        this.articleReplyId = articleReplyId;
        this.articleId = articleId;
        this.replier = replier;
        this.detail = detail;
        this.createTime = createTime;
        this.views = views;
        this.likes = likes;
        this.status = status;
    }

    public int getArticleReplyId() {
        return articleReplyId;
    }

    public void setArticleReplyId(int articleReplyId) {
        this.articleReplyId = articleReplyId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleReply)) return false;
        ArticleReply that = (ArticleReply) o;
        return articleReplyId == that.articleReplyId && articleId == that.articleId && views == that.views && likes == that.likes && status == that.status && Objects.equals(replier, that.replier) && Objects.equals(detail, that.detail) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleReplyId, articleId, replier, detail, createTime, views, likes, status);
    }

    @Override
    public String toString() {
        return "ArticleReply{" +
                "articleReplyId=" + articleReplyId +
                ", articleId=" + articleId +
                ", replier='" + replier + '\'' +
                ", detail='" + detail + '\'' +
                ", createTime=" + createTime +
                ", views=" + views +
                ", likes=" + likes +
                ", status=" + status +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Article article = null;
        try
        {
           article =(Article) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return article;
    }
}
