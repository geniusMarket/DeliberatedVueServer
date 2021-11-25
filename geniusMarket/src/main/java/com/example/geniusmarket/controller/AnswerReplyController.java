package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.AnswerReplyMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.AnswerReply;
import com.example.geniusmarket.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class AnswerReplyController {
    @Autowired
    AnswerReplyMapper answerReplyMapper;
    @Autowired
    UserMapper userMapper;
    @PostMapping("addAnswerReply")
    public JSONObject addAnswerReply(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            AnswerReply answerReply = new AnswerReply(jsonObject.getIntValue("answerId"),jsonObject.getString("openId"),
                    jsonObject.getString("detail"),jsonObject.getIntValue("reward"));
            answerReplyMapper.insertAnswerReplyByObject(answerReply);
            status.put("data",answerReply);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("data",null);
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("delAnswerReply")
    public JSONObject delAnswerRely(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            answerReplyMapper.deleteAnswerReplyById(jsonObject.getIntValue("answerReplyId"));
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("changeAnswerReply")
    public JSONObject changeAnsRpy(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            AnswerReply answerReply = answerReplyMapper.selectAnswerReplyById(jsonObject.getIntValue("answerReplyId"));
              answerReply.setDetail(jsonObject.getString("detail"));
              answerReply.setReward(jsonObject.getIntValue("reward"));
            answerReplyMapper.updateAnswerReplyByObject(answerReply);
            status.put("data",answerReply);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("data",null);
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("addAnswerReplyLikes")
    public JSONObject addAPL(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            AnswerReply answerReply = answerReplyMapper.selectAnswerReplyById(jsonObject.getIntValue("answerReplyId"));
            answerReply.setLikes(answerReply.getLikes()+1);
            answerReplyMapper.updateAnswerReplyByObject(answerReply);
            status.put("data",answerReply);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("data",null);
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("selectAnswerReply")
    public JSONObject selectAPL(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var ansRplUsers = new ArrayList<Data<AnswerReply>>();
            for(var i:answerReplyMapper.selectAnswerRepliesByAnswerId(jsonObject.getIntValue("answerId")))
                ansRplUsers.add(new Data<>(i,userMapper.selectUserByOpenId(i.getReplier())));
            JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(ansRplUsers));
            status.put("data",array);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("data",null);
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);

    }
}

