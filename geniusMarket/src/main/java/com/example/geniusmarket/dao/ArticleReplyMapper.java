package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.Annotation;
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
     * @param status
     * @return
     */
    List<ArticleReply> selectArticleRepliesByStatus(int status);
    /**
     *
     * @param id
     * @return
     */
    ArticleReply selectArticleReplyById(int id);
    /**
     *
     * @param replyId
     */
    void addLikes(int replyId);
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
    List<ArticleReply> verify();
}
