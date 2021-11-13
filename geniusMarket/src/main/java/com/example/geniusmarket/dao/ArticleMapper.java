package com.example.geniusmarket.dao;

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
     * @param article
     */
    void updateArticleByObject(Article article);

    /**
     *
     * @param id
     */
    void deleteArticleById(int id);
}
