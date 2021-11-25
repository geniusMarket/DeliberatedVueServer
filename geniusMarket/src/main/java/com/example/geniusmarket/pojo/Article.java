package com.example.geniusmarket.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Article implements Cloneable{
    public final static int EXAMINING = 0; //审核中
    public final static int EXAMINE_SUCCESS = 1;//审核成功
    public final static int EXAMINE_FAILED = 2;//审核失败
    private int articleId;
    private String title;
    private String detail;
    private Timestamp createTime;
    private String author;
    private int views=0;
    private int likes=0;
    private int reward =0;
    private int status = EXAMINE_SUCCESS;
    private int codeId;
    public Article(){
        this.createTime =new Timestamp(new Date().getTime());
    }
    public Article(String title, String detail, String author) {
        this.title = title;
        this.detail = detail;
        this.author = author;
        this.createTime =new Timestamp(new Date().getTime());
        codeId = 0;
    }

    public Article(String title, String detail, String author, int codeId) {
        this.title = title;
        this.detail = detail;
        this.author = author;
        this.codeId = codeId;
        this.createTime =new Timestamp(new Date().getTime());
    }

    public Article(int articleId, String title, String detail, Timestamp createTime, String author, int views, int likes, int reward, int status, int codeId) {
        this.articleId = articleId;
        this.title = title;
        this.detail = detail;
        this.createTime = createTime;
        this.author = author;
        this.views = views;
        this.likes = likes;
        this.reward = reward;
        this.status = status;
        this.codeId = codeId;
    }

    public static int getEXAMINING() {
        return EXAMINING;
    }

    public static int getExamineSuccess() {
        return EXAMINE_SUCCESS;
    }

    public static int getExamineFailed() {
        return EXAMINE_FAILED;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return articleId == article.articleId && views == article.views && likes == article.likes && reward == article.reward && status == article.status && codeId == article.codeId && Objects.equals(title, article.title) && Objects.equals(detail, article.detail) && Objects.equals(createTime, article.createTime) && Objects.equals(author, article.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, title, detail, createTime, author, views, likes, reward, status, codeId);
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", createTime=" + createTime +
                ", author='" + author + '\'' +
                ", views=" + views +
                ", likes=" + likes +
                ", reward=" + reward +
                ", status=" + status +
                ", codeId=" + codeId +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Article article = null;
        try {
            article =(Article) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return  article;
    }
}
