package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.ArticleMapper;
import com.example.geniusmarket.dao.LikesRecordMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.Article;
import com.example.geniusmarket.pojo.LikesRecord;
import com.example.geniusmarket.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ArticleController {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikesRecordMapper likesRecordMapper;
    @PostMapping("/addArticle")
    public JSONObject addArticle(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject myData = JSONObject.parseObject(data);
            var title = myData.getString("title");
            var detail = myData.getString("detail");
            var author = myData.getString("author");
            var codeId = myData.getIntValue("codeId");//codeId = 0时代表无代码引用
            var myArticle = new Article(title, detail, author,codeId);
            if(myData.getIntValue("type")==1)//增加帖子
                articleMapper.insertArticleByObject(myArticle);
            else //修改帖子
                articleMapper.updateArticleByObject(myArticle);
            status.put("status","success");
            status.put("data",myArticle);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return  (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("/articleLikes")
    public JSONObject articleLikes(@RequestBody String data)// articleId:int openId:string type:int
    {
        Map<String,Object> status = new HashMap<>();
        status.put("status","success");
        try
        {
            JSONObject myData= JSONObject.parseObject(data);
            var articleId = myData.getIntValue("articleId");
            var article = articleMapper.selectArticleById(articleId);
            if(myData.getIntValue("type")==1)
            {
                article.setLikes(article.getLikes() + 1);
                likesRecordMapper.insertRecord(new LikesRecord(myData.getString("openId"),article.getArticleId(),LikesRecord.ARTICLE));
            }
            else
            {
                article.setLikes(article.getLikes() - 1);
                likesRecordMapper.deleteRecord(new LikesRecord(myData.getString("openId"),article.getArticleId(),LikesRecord.ARTICLE));
            }
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
    @PostMapping("/selectArticles")
    public JSONObject articles(@RequestBody String data)// type:string request:string(not must) openId:string
    {
        var status = new HashMap<String,Object>();
        List<Article> articleList;
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            if(jsonObject.getString("type").equals("all"))
                articleList = articleMapper.selectAllArticle();
            else
                articleList = articleMapper.selectArticleByString(jsonObject.getString("request"));
            List<Data<Article>> artUsers = new ArrayList<>();
            for(var i: articleList)
            {
                artUsers.add(new Data<>(i,userMapper.selectUserByOpenId(i.getAuthor())));
            }
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<artUsers.size();i++)
            {
                LikesRecord likesRecord = new LikesRecord(jsonObject.getString("openId"),artUsers.get(i).getData().getArticleId(),LikesRecord.ARTICLE);
                if(likesRecordMapper.recordIsExists(likesRecord))list.add(Integer.valueOf(1));
                else list.add(Integer.valueOf(0));
            }
            status.put("status","success");
            status.put("data", JSONArray.parseArray(JSONObject.toJSONString(artUsers)));
            status.put("likesRecord",list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
    return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("delArticle")
    public JSONObject delArticle(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            articleMapper.deleteArticleById(jsonObject.getIntValue("articleId"));
            likesRecordMapper.deleteRecords(jsonObject.getIntValue("articleId"),LikesRecord.ARTICLE);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("/getArticle")
    public JSONObject getArticle(@RequestBody String data)//articleId:int openId:string
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var article = articleMapper.selectArticleById(jsonObject.getIntValue("articleId"));
            Data<Article> articleData = new Data<>(article,userMapper.selectUserByOpenId(article.getAuthor()));
            LikesRecord likesRecord = new LikesRecord(jsonObject.getString("openId"),articleData.getData().getArticleId(),LikesRecord.ARTICLE);
            int record = 0;
            if(likesRecordMapper.recordIsExists(likesRecord))record = 1;
            status.put("status","success");
            status.put("data",articleData);
            status.put("likesRecord",record);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
}

