package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.LikesRecordMapper;
import com.example.geniusmarket.dao.QuestionMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.LikesRecord;
import com.example.geniusmarket.pojo.Question;
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
public class QuestionController {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikesRecordMapper likesRecordMapper;
    @PostMapping("addQuestion")
    public JSONObject addQuestion(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            Question question = new Question(jsonObject.getString("openId"),jsonObject.getString("title"),
                    jsonObject.getString("detail"),jsonObject.getIntValue("codeId"),jsonObject.getIntValue("reward"));
            questionMapper.insertQuestionByObject(question);
            status.put("data",question);
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
    @PostMapping("delQuestion")
    public JSONObject delQuestion(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            questionMapper.deleteQuestionById(jsonObject.getIntValue("questionId"));
            likesRecordMapper.deleteRecords(jsonObject.getIntValue("questionId"),LikesRecord.QUESTION);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("changeQuestion")
    public JSONObject changeQuestion(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            Question question = questionMapper.selectQuestionById(jsonObject.getIntValue("questionId"));
            question.setTitle(jsonObject.getString("title"));
            question.setDetail(jsonObject.getString("detail"));
            question.setReward(jsonObject.getIntValue("reward"));
            questionMapper.updateQuestionByObject(question);
            status.put("data",question);
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
    @PostMapping("selectQuestion")
    public JSONObject selectQuestion(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            List<Question> questionList;
            var quesUser = new ArrayList<Data<Question>>();
            if(jsonObject.getIntValue("type")==1)
                questionList = questionMapper.selectQuestionsByString("%");
            else if(jsonObject.getIntValue("type")==2)
                questionList = questionMapper.selectQuestionsByString(jsonObject.getString("detail"));
            else
                questionList = questionMapper.selectQuestionByCodeId(jsonObject.getIntValue("codeId"));
            for(var i:questionList)
            {
                quesUser.add(new Data<>(i,userMapper.selectUserByOpenId(i.getAsker())));
            }
            List<Integer> list = new ArrayList<>();
            for(var i = 0;i<quesUser.size();i++)
            {
                LikesRecord likesRecord = new LikesRecord(jsonObject.getString("openId"),quesUser.get(i).getData().getQuestionId(),LikesRecord.QUESTION);
                if(likesRecordMapper.recordIsExists(likesRecord) == false)list.add(Integer.valueOf(0));
                else list.add(Integer.valueOf(1));
            }
            JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(quesUser));
            status.put("data",array);
            status.put("status","success");
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
    @PostMapping("acceptAnswer")
    public JSONObject acceptAnswer(@RequestBody String data)
    {
        JSONObject status = new JSONObject();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            questionMapper.accepted(jsonObject.getIntValue("questionId"),jsonObject.getIntValue("answerId"));
            status.put("status","success");
            status.put("question",questionMapper.selectQuestionById(jsonObject.getIntValue("questionId")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return status;
    }
    @PostMapping("getQuestionIsAccept")
    public JSONObject getStatus(@RequestBody String data)
    {
        JSONObject status = new JSONObject();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var question = questionMapper.selectQuestionById(jsonObject.getIntValue("questionId"));
            status.put("status","success");
            status.put("accept",question.getAccept());
        }
        catch (Exception e)
            {
                e.printStackTrace();
                status.put("status","error");

            }
        return status;
    }
}
