package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.ArticleReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleReplyMapper {
    /**
     *
     * @param articleReply
     */
    void insertArticleReplyByObject(ArticleReply articleReply);

    /**
     *
     * @param articleId
     * @return
     */
    List<ArticleReply> selectArticleReplysByArticleId(int articleId);

    /**
     *
     * @param articleReply
     */
    void updateArticleReplyByObject(ArticleReply articleReply);

    /**
     *
     * @param id
     */
    void deleteArticleReplyById(int id);
}
