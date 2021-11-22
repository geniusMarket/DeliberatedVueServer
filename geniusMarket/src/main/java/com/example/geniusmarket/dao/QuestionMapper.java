package com.example.geniusmarket.dao;

import com.example.geniusmarket.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {
    /**
     *
     * @param question
     */
    void insertQuestionByObject(Question question);

    /**
     *
     * @param question
     */
    void updateQuestionByObject(Question question);

    /**
     *
     * @param id
     * @return
     */
    Question selectQuestionById(int id);

    /**
     *
     * @param title
     * @return
     */
   List<Question> selectQuestionsByString(String title);

    /**
     *
     * @param status
     * @return
     */
    List<Question> selectQuestionByStatus(int status);
    /**
     *
     * @param openId
     * @return
     */
   List<Question> selectQuestionByAsker(String openId);
    /**
     *
     * @param id
     */
   void deleteQuestionById(int id);
}
