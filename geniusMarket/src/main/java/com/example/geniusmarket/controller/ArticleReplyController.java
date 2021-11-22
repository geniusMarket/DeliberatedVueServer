package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.ArticleReplyMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.ArticleReply;
import com.example.geniusmarket.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ArticleReplyController {
    @Autowired
    ArticleReplyMapper articleReplyMapper;
    @Autowired
    UserMapper userMapper;
    @PostMapping("/addReply")//帖子评论
    public JSONObject addReply(@RequestBody String data){
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject myData =JSONObject.parseObject(data);
            var articleID = myData.getIntValue("articleId");
            var detail =myData.getString("detail");
            var replier =myData.getString("replier");
            var myReply=new ArticleReply(articleID,detail, replier);
            articleReplyMapper.insertArticleReplyByObject(myReply);
            status.put("status","success");
            status.put("data",myReply);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return  (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("/ArticleReplyLikes")
    public JSONObject ArticleReplyLikes(@RequestBody String data)
    {
        Map<String,Object> status = new HashMap<>();
        status.put("status","success");
        JSONObject myData= JSONObject.parseObject(data);
        try
        {
            var articleReplyId = myData.getIntValue("articleReplyId");
            articleReplyMapper.addLikes(articleReplyId);
            return (JSONObject) JSONObject.toJSON(status);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.replace("status","error");
            return (JSONObject) JSONObject.toJSON(status);
        }
    }
    @PostMapping("/delReply")//删除帖子评论
    public JSONObject delReply(@RequestBody String data){
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject myData =JSONObject.parseObject(data);
            articleReplyMapper.deleteArticleReplyById(myData.getIntValue("articleReplyId"));
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return  (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("" +
            "" +
            "" +
            "")//显示评论
    public JSONObject replies(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject myData =JSONObject.parseObject(data);
            var articles = articleReplyMapper.selectArticleReplysByArticleId(myData.getIntValue("articleId"));
            var arpUser = new ArrayList<Data<ArticleReply>>();
            for(var i:articles)
                arpUser.add(new Data<>(i,userMapper.selectUserByOpenId(i.getReplier())));
            JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(arpUser));
            status.put("status","success");
            status.put("data",array);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return  (JSONObject) JSONObject.toJSON(status);
    }
}

