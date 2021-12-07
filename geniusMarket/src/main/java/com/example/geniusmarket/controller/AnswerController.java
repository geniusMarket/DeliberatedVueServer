package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.AnswerMapper;
import com.example.geniusmarket.dao.LikesRecordMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.Answer;
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

@RestController
@CrossOrigin
public class AnswerController {
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikesRecordMapper likesRecordMapper;
    @PostMapping("addAnswer")
    public JSONObject addAnswer(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSON.parseObject(data);
            Answer answer = new Answer(jsonObject.getString("answerer"),jsonObject.getIntValue("questionId"),
                    jsonObject.getString("detail"),jsonObject.getIntValue("reward"));
            answerMapper.insertAnswerByObject(answer);
            status.put("data",answer);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("delAnswer")
    public JSONObject delAnswer(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            answerMapper.deleteAnswerById(jsonObject.getIntValue("answerId"));
            likesRecordMapper.deleteRecords(jsonObject.getIntValue("answerId"),LikesRecord.ANSWER);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("changeAnswer")
    public JSONObject changeAnswer(@RequestBody String data)
    {
        var status =new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            Answer answer = answerMapper.selectAnswerById(jsonObject.getIntValue("answerId"));
            answer.setDetail(jsonObject.getString("detail"));
            answer.setReward(jsonObject.getIntValue("reward"));
            answerMapper.updateAnswerByObject(answer);
            status.put("data",answer);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("addAnswerLikes")
    public JSONObject addAnswerLikes(@RequestBody String data)//data answerId:int openId:string type:int
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            int answerId = jsonObject.getIntValue("answerId");
            var answer = answerMapper.selectAnswerById(answerId);
            if(jsonObject.getIntValue("type")==1)//1为添加 2为删除
            {
                answer.setLikes(answer.getLikes()+1);
                likesRecordMapper.insertRecord(new LikesRecord(jsonObject.getString("openId"),answerId,LikesRecord.ANSWER));
            }
            else
            {
                answer.setLikes(answer.getLikes()-1);
                likesRecordMapper.deleteRecord(new LikesRecord(jsonObject.getString("openId"),answerId,LikesRecord.ANSWER));
            }
            answerMapper.updateAnswerByObject(answer);
            status.put("data",answer);
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
    @PostMapping("selectAnswer")
    public JSONObject selectAnswer(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var ansUsers = new ArrayList<Data<Answer>>();
            for(var i:answerMapper.selectAnswerInQuestion(jsonObject.getIntValue("questionId")))
            {
                ansUsers.add(new Data<>(i,userMapper.selectUserByOpenId(i.getAnswerer())));
            }
            List<Integer>list = new ArrayList<>();
            for(int i = 0;i<ansUsers.size();i++)
            {
                LikesRecord likesRecord = new LikesRecord(jsonObject.getString("openId"),ansUsers.get(i).getData().getAnswerId(),LikesRecord.ANSWER);
                if(likesRecordMapper.recordIsExists(likesRecord) == false)list.add(Integer.valueOf(0));
                else list.add(Integer.valueOf(1));
            }
            JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(ansUsers));
            status.put("status","success");
            status.put("data",array);
            status.put("likesRecord",list);
        }catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return  (JSONObject) JSONObject.toJSON(status);
    }

}

