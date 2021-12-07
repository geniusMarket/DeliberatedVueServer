package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.ArticleReplyMapper;
import com.example.geniusmarket.dao.LikesRecordMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.ArticleReply;
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
public class ArticleReplyController {
    @Autowired
    ArticleReplyMapper articleReplyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikesRecordMapper likesRecordMapper;
    @PostMapping("/addArticleReply")//帖子评论
    public JSONObject addReply(@RequestBody String data){
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject myData =JSONObject.parseObject(data);
            var articleID = myData.getIntValue("articleId");
            var detail =myData.getString("detail");
            var replier =myData.getString("replier");
            var myReply=new ArticleReply(articleID,replier,detail);
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
    @PostMapping("/articleReplyLikes")
    public JSONObject ArticleReplyLikes(@RequestBody String data)// type:int openId:int articleReplyId:int
    {
        Map<String,Object> status = new HashMap<>();
        status.put("status","success");
        try
        {
            JSONObject myData= JSONObject.parseObject(data);
            var articleReplyId = myData.getIntValue("articleReplyId");
            var articleReply = articleReplyMapper.selectArticleReplyById(articleReplyId);
            if(myData.getIntValue("type")==1)
            {
                articleReply.setLikes(articleReply.getLikes()+1);
                LikesRecord likesRecord = new LikesRecord(myData.getString("openId"),articleReply.getArticleReplyId(),LikesRecord.ARTICLE_REPLY);
                likesRecordMapper.insertRecord(likesRecord);
            }
            else
            {
                articleReply.setLikes(articleReply.getLikes()-1);
                LikesRecord likesRecord = new LikesRecord(myData.getString("openId"),articleReply.getArticleReplyId(),LikesRecord.ARTICLE_REPLY);
                likesRecordMapper.deleteRecord(likesRecord);
            }
            articleReplyMapper.updateArticleReplyByObject(articleReply);
            status.put("data",articleReply);
            return (JSONObject) JSONObject.toJSON(status);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.replace("status","error");
            return (JSONObject) JSONObject.toJSON(status);
        }
    }
    @PostMapping("/delArticleReply")//删除帖子评论
    public JSONObject delReply(@RequestBody String data){
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject myData =JSONObject.parseObject(data);
            articleReplyMapper.deleteArticleReplyById(myData.getIntValue("articleReplyId"));
            likesRecordMapper.deleteRecords(myData.getIntValue("articleReplyId"),LikesRecord.ARTICLE_REPLY);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return  (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("/articleReplies")//显示评论
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
            List<Integer> list = new ArrayList<>();
            for(int i =0;i<arpUser.size();i++)
            {
             LikesRecord likesRecord = new LikesRecord(myData.getString("openId"),arpUser.get(i).getData().getArticleReplyId(),LikesRecord.ARTICLE_REPLY);
                if(likesRecordMapper.recordIsExists(likesRecord) == false)list.add(Integer.valueOf(0));
                else list.add(Integer.valueOf(1));
            }
            JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(arpUser));
            status.put("status","success");
            status.put("data",array);
            status.put("likesRecord",list);
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

