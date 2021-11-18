package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.ArticleReplyMapper;
import com.example.geniusmarket.pojo.ArticleReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ArticleReplyController {
    @Autowired
    ArticleReplyMapper articleReplyMapper;
    @PostMapping("/reply")
    public String addReply(@RequestBody String data){

        JSONObject myData =JSONObject.parseObject(data);
        try
        {
            var articleReplyID =myData.getIntValue("articleReplyID");
            var articleID = myData.getIntValue("article");
            var detail =myData.getString("detail");
            var replier =myData.getString("replier");
            var myReply=new ArticleReply(articleID, articleReplyID, detail, replier);
            articleReplyMapper.insertArticleReplyByObject(myReply);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
        return  "status";
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
            var articleReply = articleReplyMapper.addLikes(articleReplyId);
            articleReply.setLikes(articleReply.getLikes()+1);
            articleReplyMapper.updateArticleReplyByObject(articleReply);
            status.put("data",articleReply);
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
