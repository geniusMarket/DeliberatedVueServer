package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.AnnotationReplyMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.AnnotationReply;
import com.example.geniusmarket.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class AnnotationReplyController {
    @Autowired
    AnnotationReplyMapper annotationReplyMapper;
    @Autowired
    UserMapper userMapper;
    @PostMapping("addAnnotationReply")
    public JSONObject addAnnotationReply(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            AnnotationReply annotationReply = new AnnotationReply(jsonObject.getString("userId"),jsonObject.getIntValue("annotationId"),
                    jsonObject.getString("detail"));
        annotationReplyMapper.insertAnnotationReplyByObject(annotationReply);
        status.put("status","success");
        status.put("data",annotationReply);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("delAnnotationReply")
    public JSONObject delAnnotationReply(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            annotationReplyMapper.deleteAnnotationReplyById(jsonObject.getIntValue("annotationReplyId"));
            status.put("status","success");
        }catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("annotationReplyLikes")
    public JSONObject anoRepLikes(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var anoRep = annotationReplyMapper.selectAnnotationReplyById(jsonObject.getIntValue("replyId"));
            anoRep.setLikes(anoRep.getLikes()+1);
            annotationReplyMapper.updateAnnotationReplyByObject(anoRep);
            status.put("status","success");
            status.put("data",anoRep);
        }catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("changeAnnotationReply")
    public JSONObject changeAnoRpl(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var anoRep = annotationReplyMapper.selectAnnotationReplyById(jsonObject.getIntValue("replyId"));
            anoRep.setDetail(jsonObject.getString("detail"));
            annotationReplyMapper.updateAnnotationReplyByObject(anoRep);
            status.put("status","success");
            status.put("data",anoRep);
        }catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("selectAnnotationReply")
    public JSONObject select(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var anoRpl = annotationReplyMapper.selectAnnotationsReplyByAnoId(jsonObject.getIntValue("annotationId"));
            var anpUser = new ArrayList<Data<AnnotationReply>>();
            for(var i:anoRpl)
            {
                anpUser.add( new Data<>(i,userMapper.selectUserByOpenId(i.getUserId())));
            }
            JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(anpUser));
            status.put("data",array);
            status.put("status","success");
        }catch (Exception e)
        {
         e.printStackTrace();
            status.put("data",null);
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
}

