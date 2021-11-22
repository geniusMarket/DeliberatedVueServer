package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnswerMapper {
    /**
     *
     * @param answer
     */
    void insertAnswerByObject(Answer answer);

    /**
     *
     * @param id
     * @return
     */
    Answer selectAnswerById(int id);

    /**
     *
     * @param str
     * @return
     */
    List<Answer> selectAnswersByString(String str);

    /**
     *
     * @param questionId
     * @return
     */
    List<Answer> selectAnswerInQuestion(int questionId);

    /**
     * @param status
     * @return
     */
    List<Answer> selectAnswerByStatus(int status);
    /**
     *
     * @param answer
     */
    void updateAnswerByObject(Answer answer);

    /**
     *
     */
    void addAnswerLikes(int answerId);
    /**
     *
     * @param id
     */
    void deleteAnswerById(int id);
}
