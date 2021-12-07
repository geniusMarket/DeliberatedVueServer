package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.AnnotationReplyMapper;
import com.example.geniusmarket.dao.LikesRecordMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.AnnotationReply;
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
public class AnnotationReplyController {
    @Autowired
    AnnotationReplyMapper annotationReplyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikesRecordMapper likesRecordMapper;
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
            likesRecordMapper.deleteRecords(jsonObject.getIntValue("annotationReplyId"),LikesRecord.ANNOTATION_REPLY);
            status.put("status","success");
        }catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
    @PostMapping("annotationReplyLikes")
    public JSONObject anoRepLikes(@RequestBody String data)// data need replyId:int type:int openId:string
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var anoRep = annotationReplyMapper.selectAnnotationReplyById(jsonObject.getIntValue("replyId"));
            if(jsonObject.getIntValue("type")==1)//1 为点赞 0为取消点赞
            {
                anoRep.setLikes(anoRep.getLikes()+1);
                LikesRecord likesRecord = new LikesRecord(jsonObject.getString("openId"),jsonObject.getIntValue("replyId"),LikesRecord.ANNOTATION_REPLY);
                likesRecordMapper.insertRecord(likesRecord);
            }
            else
            {
                anoRep.setLikes(anoRep.getLikes()-1);
                LikesRecord likesRecord = new LikesRecord(jsonObject.getString("openId"),jsonObject.getIntValue("replyId"),LikesRecord.ANNOTATION_REPLY);
                likesRecordMapper.deleteRecord(likesRecord);
            }
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
    public JSONObject select(@RequestBody String data)//data need annotationId:int openId:string
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
            List<Integer> likesList = new ArrayList<>();
            for(int i =0;i<anpUser.size();i++)
            {
                LikesRecord likesRecord = new LikesRecord(jsonObject.getString("openId"),anoRpl.get(i).getReplyId(),LikesRecord.ANNOTATION_REPLY);
                if(likesRecordMapper.recordIsExists(likesRecord))likesList.add(Integer.valueOf(1));
                else likesList.add(Integer.valueOf(0));
            }
            JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(anpUser));
            status.put("data",array);
            status.put("status","success");
            status.put("likesRecord",likesList);
        }catch (Exception e)
        {
         e.printStackTrace();
            status.put("data",null);
            status.put("status","error");
        }
        return (JSONObject) JSONObject.toJSON(status);
    }
}

