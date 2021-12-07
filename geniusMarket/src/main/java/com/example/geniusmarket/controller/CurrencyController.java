package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.*;
import com.example.geniusmarket.pojo.*;
import com.example.geniusmarket.utils.Data;
import com.example.geniusmarket.utils.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;


@RestController
@CrossOrigin
public class CurrencyController {
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

    @PostMapping("/del/{type}/{id}")
    public JSONObject del(@PathVariable("type") String type, @PathVariable("id") int id) {

        var status = new HashMap<String, Object>();
        try {
            switch (Dictionary.pojoHashMap().get(type)) {
                case 1:
                    annotationMapper.deleteAnnotationById(id);
                    break;
                case 2:
                    annotationReplyMapper.deleteAnnotationReplyById(id);
                    break;
                case 3:
                    answerMapper.deleteAnswerById(id);
                    break;
                case 4:
                    answerReplyMapper.deleteAnswerReplyById(id);
                    break;
                case 5:
                    articleMapper.deleteArticleById(id);
                    break;
                case 6:
                    articleReplyMapper.deleteArticleReplyById(id);
                    break;
                case 7:
                case 11:
                case 12:
                case 9:
                case 8:
                    break;
                case 10:
                    questionMapper.deleteQuestionById(id);
                    break;
            }
            status.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            status.put("status", "dataBase Error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }

    @PostMapping("addLike/{type}/{id}")
    public JSONObject addLike(@PathVariable("type") String type, @PathVariable("id") int id) {
        var status = new HashMap<String, Object>();
        try {
            switch (Dictionary.pojoHashMap().get(type)) {
                case 1: {
                    var annotation = annotationMapper.selectAnnotationById(id);
                    annotation.setLikes(annotation.getLikes() + 1);
                    annotationMapper.updateAnnotationByObject(annotation);
                    var user = userMapper.selectUserByOpenId(annotation.getUserId());
                    Data<Annotation> data = new Data<>(annotation, user);
                    status.put("data", data);
                    break;
                }
                case 2: {
                    var annotationReply = annotationReplyMapper.selectAnnotationReplyById(id);
                    annotationReply.setLikes(annotationReply.getLikes() + 1);
                    annotationReplyMapper.updateAnnotationReplyByObject(annotationReply);
                    var user = userMapper.selectUserByOpenId(annotationReply.getUserId());
                    Data<AnnotationReply> data = new Data<>(annotationReply, user);
                    status.put("data", data);
                    break;
                }
                case 3: {
                    var answer = answerMapper.selectAnswerById(id);
                    answer.setLikes(answer.getLikes() + 1);
                    answerMapper.updateAnswerByObject(answer);
                    var user = userMapper.selectUserByOpenId(answer.getAnswerer());
                    Data<Answer> data = new Data<>(answer, user);
                    status.put("data", data);
                    break;
                }
                case 4: {
                    var answerReply = answerReplyMapper.selectAnswerReplyById(id);
                    answerReply.setLikes(answerReply.getLikes() + 1);
                    answerReplyMapper.updateAnswerReplyByObject(answerReply);
                    var user = userMapper.selectUserByOpenId(answerReply.getReplier());
                    Data<AnswerReply> data = new Data<>(answerReply, user);
                    status.put("data", data);
                    break;
                }
                case 5: {
                    var article = articleMapper.selectArticleById(id);
                    article.setLikes(article.getLikes() + 1);
                    articleMapper.updateArticleByObject(article);
                    var user = userMapper.selectUserByOpenId(article.getAuthor());
                    Data<Article> data = new Data<>(article, user);
                    status.put("data", data);
                    break;
                }
                case 6: {
                    var articleReply = articleReplyMapper.selectArticleReplyById(id);
                    articleReply.setLikes(articleReply.getLikes() + 1);
                    articleReplyMapper.updateArticleReplyByObject(articleReply);
                    var user = userMapper.selectUserByOpenId(articleReply.getReplier());
                    Data<ArticleReply> data = new Data<>(articleReply, user);
                    status.put("data", data);
                    break;
                }
                case 7:
                case 11:
                case 12:
                case 9:
                case 8:
                    break;
                case 10: {
                    var question = questionMapper.selectQuestionById(id);
                    question.setLikes(question.getLikes() + 1);
                    questionMapper.updateQuestionByObject(question);
                    var user = userMapper.selectUserByOpenId(question.getAsker());
                    Data<Question> data = new Data<>(question, user);
                    status.put("data", data);
                    break;
                }
            }
            status.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            status.put("status", "dataBase Error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }

    @PostMapping("changeStatus/{type}/{id}/{newStatus}")
    public JSONObject change(@PathVariable("type") String type, @PathVariable("id") int id, @PathVariable("newStatus") int newStatus) {
        var status = new JSONObject();
        try {
            switch (Dictionary.pojoHashMap().get(type)) {
                case 1:
                    annotationMapper.setStatus(id,newStatus);
                    break;
                case 2:
                    annotationReplyMapper.setStatus(id,newStatus);
                    break;
                case 3:
                    answerMapper.setStatus(id,newStatus);
                    break;
                case 4:
                    answerReplyMapper.setStatus(id,newStatus);
                    break;
                case 5:
                    articleMapper.setStatus(id,newStatus);
                    break;
                case 6:
                    articleReplyMapper.setStatus(id,newStatus);
                    break;
                case 7:
                case 11:
                case 12:
                case 9:
                case 8:
                    break;
                case 10:
                    questionMapper.setStatus(id,newStatus);
                    break;
            }
            status.put("status","success");
        } catch (Exception e) {
            status.put("status","error");
            e.printStackTrace();
        }
        return status;
    }
    @PostMapping("getVerify")
    public JSONObject verify()
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            var annotationList = annotationMapper.verify();
            var annotationReplyList = annotationReplyMapper.verify();
            var answerList = answerMapper.verify();
            var answerReplyList = answerReplyMapper.verify();
            var articleList = articleMapper.verify();
            var articleReplyList = articleReplyMapper.verify();
            var questionList = questionMapper.verify();
            jsonObject.put("annotations",annotationList);
            jsonObject.put("annotationReplies",annotationReplyList);
            jsonObject.put("answers",answerList);
            jsonObject.put("answerReplies",answerReplyList);
            jsonObject.put("articles",articleList);
            jsonObject.put("articleReplies",articleReplyList);
            jsonObject.put("questions",questionList);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            jsonObject.put("status","parse to Json failed");
        } catch (Exception e)
        {
            e.printStackTrace();
            jsonObject.put("status","unknown error");
        }
        return jsonObject;
    }
}
