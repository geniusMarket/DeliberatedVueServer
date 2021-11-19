package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.ArticleMapper;
import com.example.geniusmarket.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleMapper articleMapper;
    @PostMapping("/addArticle")
    public String addArticle(@RequestBody String data)
    {
        JSONObject myData = JSONObject.parseObject(data);
        try {
            var title = myData.getString("title");
            var detail = myData.getString("detail");
            var author = myData.getString("author");
            var articleID = myData.getIntValue("articleID");
            var myArticle = new Article(title, detail, author, articleID);
            articleMapper.insertArticleByObject(myArticle);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
        return  "status";
    }
    @PostMapping("/ArticleLikes")
    public JSONObject ArticleLikes(@RequestBody String data)
    {
        Map<String,Object> status = new HashMap<>();
        status.put("status","success");
        JSONObject myData= JSONObject.parseObject(data);
        try
        {
            var articleId = myData.getIntValue("articleId");
            var article = articleMapper.selectArticleById(articleId);
            article.setLikes(article.getLikes()+1);
            articleMapper.updateArticleByObject(article);
            status.put("data",article);
            return (JSONObject) JSONObject.toJSON(status);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.replace("status","error");
            status.put("data",null);
            return (JSONObject) JSONObject.toJSON(status);
        }
    }
}
