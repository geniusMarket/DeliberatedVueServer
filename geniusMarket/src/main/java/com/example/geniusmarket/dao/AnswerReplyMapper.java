package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.Annotation;
import com.example.geniusmarket.pojo.AnswerReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnswerReplyMapper {
    /**
     *
     * @param answerReply
     */
    void insertAnswerReplyByObject(AnswerReply answerReply);

    /**
     *
     * @param id
     * @return
     */
    AnswerReply selectAnswerReplyById(int id);

    /**
     *
     * @param answerId
     * @return
     */
    List<AnswerReply> selectAnswerRepliesByAnswerId(int answerId);

    /**
     *
     * @param str
     * @return
     */
    List<AnswerReply> selectAnswerRepliesByString(String str);

    /**
     *
     * @param status
     * @return
     */
    List<AnswerReply> selectAnswerRepliesByStatus(int status);
    /**
     *
     * @param answerReply
     */
    void updateAnswerReplyByObject(AnswerReply answerReply);

    /**
     *
     * @param id
     */
    void deleteAnswerReplyById(int id);

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
    List<AnswerReply> verify();
}
