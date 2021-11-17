package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.HistoryMapper;
import com.example.geniusmarket.dao.QuestionMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.Question;
import com.example.geniusmarket.pojo.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    QuestionMapper questionMapper;
    private final String appIdLocal = "wxfd487c0b4621b54f";
    private final String appId ="wxb9cbbbf20877369d";
    private final String appSecretLocal = "92b1d5c833b7cbaf01bdd5f6a4a97ce8";
    private final String appSecret = "59e8423f2e4bca4c6270f10431cc2e37";
    /**
     * 获取微信小程序 用户信息或用户手机号码
     * @param data 调用微信登陆返回的Code
     * @return
     */
    @RequestMapping("/login")
    @Transactional(rollbackFor = Exception.class)
    public synchronized JSONObject getUserInfo(@RequestBody String data) throws Exception {
        var status = new HashMap<String,Object>();
        status.put("status","success");
        try {
            JSONObject jsonObject = JSON.parseObject(data);
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
            var userInfo = userProfile.getJSONObject("userInfo").toString();
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
