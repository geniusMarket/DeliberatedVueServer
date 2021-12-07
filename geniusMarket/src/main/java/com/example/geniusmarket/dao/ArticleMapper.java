package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.Annotation;
import com.example.geniusmarket.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleMapper {
    /**
     *
     * @param article
     */
    void insertArticleByObject(Article article);

    /**
     *
     * @param id
     * @return
     */
    Article selectArticleById(int id);

    /**
     *
     * @param str
     * @return
     */
    List<Article> selectArticleByString(String str);

    /**
     *
     * @param status
     * @return
     */
    List<Article> selectArticleByStatus(int status);
    /**
     *
     * @param article
     */
    void updateArticleByObject(Article article);

    /**
     *
     * @param id
     */
    void deleteArticleById(int id);

    /**
     *
     * @return
     */
    List<Article> selectAllArticle();

    /**
     *
     * @param id
     * @param status
     */
    void setStatus(int id,int status);
    /**
     *
     * @return
     */
    List<Article> verify();

    /**
     *
     * @param author
     * @return
     */
    List<Article> selectArticleByAuthor(String author);
}
