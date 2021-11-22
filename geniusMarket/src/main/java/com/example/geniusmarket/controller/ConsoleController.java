package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.*;
import com.example.geniusmarket.pojo.*;
import com.example.geniusmarket.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ConsoleController {
    @Autowired
    AnnotationMapper annotationMapper;
    @Autowired
    AnnotationReplyMapper annotationReplyMapper;
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    AnswerReplyMapper answerReplyMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleReplyMapper articleReplyMapper;
    @Autowired
    FansMapper fansMapper;
    @Autowired
    FavoriteMapper favoriteMapper;
    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    SourceCodeMapper sourceCodeMapper;
    @Autowired
    UserMapper userMapper;
    @PostMapping("/showExamining")
    public JSONObject showExamining()
    {

        var status = new HashMap<String,Object>();
        JSONArray jsonArray;
        List<Object> objectList = new ArrayList<>();
        try {
            for (var i : annotationMapper.selectAnnotationByStatus(Annotation.EXAMINING)) {
                Data<Annotation> data = new Data<>(i, userMapper.selectUserByOpenId(i.getUserId()));
                objectList.add(data);
            }
            for (var i : annotationReplyMapper.selectAnnotationsReplyByStatus(AnnotationReply.EXAMINING)) {
                Data<AnnotationReply> data = new Data<>(i, userMapper.selectUserByOpenId(i.getUserId()));
                objectList.add(data);
            }
            for(var i:answerMapper.selectAnswerByStatus(Answer.EXAMINING))
            {
                Data<Answer> data = new Data<>(i,userMapper.selectUserByOpenId(i.getAnswerer()));
                objectList.add(data);
            }
            for(var i:answerReplyMapper.selectAnswerRepliesByStatus(AnswerReply.EXAMINING))
            {
                Data<AnswerReply> data = new Data<>(i,userMapper.selectUserByOpenId(i.getReplier()));
                objectList.add(data);
            }
            for(var i:articleMapper.selectArticleByStatus(Article.EXAMINING))
            {
                Data<Article> data = new Data<>(i,userMapper.selectUserByOpenId(i.getAuthor()));
                objectList.add(data);
            }
            for(var i:articleReplyMapper.selectArticleRepliesByStatus(ArticleReply.EXAMINING))
            {
                Data<ArticleReply> data = new Data<>(i,userMapper.selectUserByOpenId(i.getReplier()));
                objectList.add(data);
            }
            for(var i:questionMapper.selectQuestionByStatus(Question.EXAMINING))
            {
                Data<Question> data = new Data<>(i,userMapper.selectUserByOpenId(i.getAsker()));
                objectList.add(data);
            }
            jsonArray = JSONArray.parseArray(JSON.toJSONString(objectList));
            status.put("data",jsonArray);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            status.put("status","JSON Error!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","Other Error!");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
}
