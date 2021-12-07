package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.*;
import com.example.geniusmarket.pojo.*;
import com.example.geniusmarket.utils.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    FavoriteMapper favoriteMapper;
    @Autowired
    FansMapper fansMapper;
    @Autowired
    ArticleMapper articleMapper;
    private final String appIdLocal = "wxfd487c0b4621b54f";
    private final String appId ="wxb9cbbbf20877369d";
    private final String appSecretLocal = "92b1d5c833b7cbaf01bdd5f6a4a97ce8";
    private final String appSecret = "59e8423f2e4bca4c6270f10431cc2e37";
    /**
     * 获取微信小程序 用户信息
     * @param data 调用微信登陆返回的Code
     * @return
     */
    @RequestMapping("/login")
    @Transactional(rollbackFor = Exception.class)
    public  JSONObject getUserInfo(@RequestBody String data){
        var status = new HashMap<String,Object>();
        status.put("status","success");
        try {
            JSONObject jsonObject = JSON.parseObject(data);
            System.out.println(data);
            String code = jsonObject.getString("code");
            JSONObject userProfile = jsonObject.getJSONObject("userProfile");//
//            String iv = userProfile.getString("iv");
//            String encryptedData = userProfile.getString("encryptedData");
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //发送get请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
            HttpGet httpget = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code");
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            var jsonObjectUser = JSON.parseObject(EntityUtils.toString(entity));
            System.out.println(jsonObjectUser);
            var openId = jsonObjectUser.getString("openid");
            var userInfo = userProfile.toString();
            var user = new User(openId,userInfo,0,0,0);
            var user1 = userMapper.selectUserByOpenId(user.getOpenId());
            if(user1 ==null) {
                userMapper.insertUserByObject(user);
                status.put("userInfo",JSONObject.parseObject(user.getUserInfo()));
                status.put("openId",user.getOpenId());
                status.put("fans",user.getFans());
                status.put("score",user.getScore());
                status.put("attention",user.getAttention());
            }
            else
            {
                status.put("userInfo",JSONObject.parseObject(user1.getUserInfo()));
                status.put("openId",user1.getOpenId());
                status.put("fans",user1.getFans());
                status.put("score",user1.getScore());
                status.put("attention",user1.getAttention());
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            status.replace("status","error");
            return (JSONObject) JSONObject.toJSON(status);
        }
        System.out.println(JSONObject.toJSON(status).toString());
        return (JSONObject) JSONObject.toJSON(status);
    }
    @RequestMapping("/myHistory")
    public JSONObject myHistory(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        status.put("status","success");
        try{
            JSONObject jsonObject = JSON.parseObject(data);
            List<MyHistory> myHistoryList = new ArrayList<>();
            var histories = historyMapper.selectHistoryByOpenId(jsonObject.getString("openId"));
            for(var i:histories)
            {
                MyHistory myHistory = new MyHistory(questionMapper.selectQuestionById(i.getQuestionId()),i.getCreateTime());
                myHistoryList.add(myHistory);
            }
            JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(myHistoryList));
            status.put("data",array);
            return (JSONObject) JSONObject.toJSON(status);
        }catch (Exception e){
            e.printStackTrace();
            status.put("data",null);
            status.replace("status","error");
            return (JSONObject) JSONObject.toJSON(status);
        }
    }
    @PostMapping("/addHistory")
    public JSONObject addHistory(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            History history = new History(jsonObject.getString("openId"),jsonObject.getIntValue("questionId"));
            historyMapper.insertHistoryByObject(history);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return (JSONObject) JSON.toJSON(status);
    }
    @PostMapping("/myQuestion")
    public JSONObject myQuestion(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        status.put("status","success");
        JSONObject jsonObject = JSONObject.parseObject(data);
        try{
            var questions = questionMapper.selectQuestionByAsker(jsonObject.getString("openId"));
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(questions));
            status.put("data",array);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("data",null);
            status.replace("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
     }
     @PostMapping("myFavorite")
    public JSONObject myFavorite(@RequestBody String data)
     {
         var status = new HashMap<String,Object>();
         status.put("status","success");
         try
         {
             JSONObject jsonObject = JSONObject.parseObject(data);
             var favorites = favoriteMapper.selectFavoritesByOpenId(jsonObject.getString("openId"));
             var questions = new ArrayList<Question>();
             for(var i:favorites)
             {
                 questions.add(questionMapper.selectQuestionById(i.getQuestionId()));
             }
             JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(questions));
             status.put("data",jsonArray);
         }
         catch (Exception e)
         {
             e.printStackTrace();
             status.put("data",null);
             status.replace("status","error");
         }
         return (JSONObject) JSONObject.toJSON(status);
     }
    @PostMapping("dealFavorite")
    public JSONObject addFavorite(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        status.put("status","success");
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            Favorite favorite = new Favorite(jsonObject.getString("openId"),jsonObject.getIntValue("questionId"));
            if(jsonObject.getIntValue("type")==1) {//增加一个关注
                favoriteMapper.insertFavoriteByObject(favorite);
            }
            else {//删除一条关注记录
                favoriteMapper.deleteFavoriteByObject(favorite);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.replace("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("fans")
    public JSONObject myFans(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        status.put("status","success");
        try{
            List<Data<Integer>> users = new ArrayList<>();// 0为互相关注 1为未关注粉丝 2未关注的对象未关注你
            JSONObject jsonObject = JSONObject.parseObject(data);
            int type = jsonObject.getIntValue("type");
            String openId = jsonObject.getString("openId");
            if(type == 1)//查找我的粉丝
            {
                for(var i:userMapper.selectFansByOpenId(openId)){
                    if(fansMapper.getAFans(openId,i.getOpenId())==null)
                    {
                        users.add(new Data<>(1,i));
                    }
                    else
                    {
                        users.add(new Data<>(0,i));
                    }
                }

            }
            else  //查找我的关注
            {
                for(var i:userMapper.selectAttentionsByOpenId(openId)){
                    if(fansMapper.getAFans(i.getOpenId(),openId) == null)
                    {
                        users.add(new Data<>(2,i));
                    }
                    else
                    {
                        users.add(new Data<>(0,i));
                    }
                }
            }
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(users));
            status.put("data",array);
        }catch (Exception e)
        {
         e.printStackTrace();
         status.replace("status","error");
         status.put("data",null);
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("dealFans")
    public JSONObject addFans(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            Fans fans = new Fans(jsonObject.getString("fansId"), jsonObject.getString("attentionId"));
            if(jsonObject.getIntValue("type")==1) {//添加关注
                fansMapper.insertFansByObject(fans);
            }
            else {//删除关注
                fansMapper.deleteFansByObject(fans);
            }
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("addScore")
    public JSONObject addScore(@RequestBody String data)//type 1加分 2减分
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            User user = userMapper.selectUserByOpenId(jsonObject.getString("openId"));
            if(jsonObject.getIntValue("type")==1)
            user.setScore(user.getScore()+jsonObject.getIntValue("reward"));
            else
                user.setScore(user.getScore()-jsonObject.getIntValue("reward"));
            userMapper.updateUserByObject(user);
            status.put("status","success");
            status.put("data",user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
//    @PostMapping("/judgeAttentionEachOther")
//    public JSONObject judgeAttentionEachOther(@RequestBody String data)
//    {
//        var status = new HashMap<String,Object>();
//        try
//        {
//
//        }
//    }
    @PostMapping("myArticle")
    public JSONObject myArticle(@RequestBody String data)
    {
        JSONObject status = new JSONObject();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var list = articleMapper.selectArticleByAuthor(jsonObject.getString("openId"));
            status.put("data",list);
            status.put("status","success");
        }catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
            return status;
    }
    @PostMapping("/getScore")
    public JSONObject getScore(@RequestBody String data)
    {
        JSONObject status = new JSONObject();
        try
        {
            JSONObject jsonObject=JSONObject.parseObject(data);
            var openId = jsonObject.getString("openId");
            var user = userMapper.selectUserByOpenId(openId);
            status.put("score",user.getScore());
            status.put("status","success");
        }
        catch (Exception e)
        {
            status.put("status","error");
        }
        return status;
    }
 }
class MyHistory
{
    Question question;
    Timestamp viewTime;

    public MyHistory(Question question, Timestamp viewTime) {
        this.question = question;
        this.viewTime = viewTime;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Timestamp getViewTime() {
        return viewTime;
    }

    public void setViewTime(Timestamp viewTime) {
        this.viewTime = viewTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyHistory)) return false;
        MyHistory myHistory = (MyHistory) o;
        return Objects.equals(question, myHistory.question) && Objects.equals(viewTime, myHistory.viewTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, viewTime);
    }

    @Override
    public String
    toString() {
        return "MyHistory{" +
                "question=" + question +
                ", viewTime=" + viewTime +
                '}';
    }
}
