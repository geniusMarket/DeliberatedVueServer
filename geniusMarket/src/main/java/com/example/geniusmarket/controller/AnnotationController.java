package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.AnnotationMapper;
import com.example.geniusmarket.dao.LikesRecordMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.Annotation;
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
public class AnnotationController {
    @Autowired
    AnnotationMapper annotationMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikesRecordMapper likesRecordMapper;
    @PostMapping("addAnnotation")
    public JSONObject addAnnotation(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSON.parseObject(data);
            Annotation annotation = new Annotation(jsonObject.getString("filePath"),jsonObject.getString("moduleName"),
                    jsonObject.getString("detail"), jsonObject.getString("userId"));
            annotationMapper.insertAnnotationByObject(annotation);
            status.put("status","success");
            status.put("data",annotation);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return (JSONObject) JSON.toJSON(status);
    }
    @PostMapping("changeAnnotation")
    public JSONObject changeAnnotation(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSON.parseObject(data);
            Annotation annotation = annotationMapper.selectAnnotationById(jsonObject.getIntValue("annotationId"));
            annotation.setDetail(jsonObject.getString("detail"));
            annotationMapper.updateAnnotationByObject(annotation);
            status.put("status","success");
            status.put("data",annotation);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("data",null);
        }
        return (JSONObject) JSON.toJSON(status);
    }
    @PostMapping("annotationLikes")//点赞 or 取消点赞
    public JSONObject annotationLikes(@RequestBody String data)// data need annotationId:int openId:string type:int
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSON.parseObject(data);
            var annotation = annotationMapper.selectAnnotationById(jsonObject.getIntValue("annotationId"));
            if(jsonObject.getIntValue("type")==1)//type = 1 点赞，type = 0 取消点赞
            {
                annotation.setLikes(annotation.getLikes() + 1);
                LikesRecord likesRecord = new LikesRecord(jsonObject.getString("openId"),annotation.getAnnotationId(),LikesRecord.ANNOTATION);
                likesRecordMapper.insertRecord(likesRecord);
            }
            else
            {
                annotation.setLikes(annotation.getLikes() - 1);
                LikesRecord likesRecord = new LikesRecord(jsonObject.getString("openId"),annotation.getAnnotationId(),LikesRecord.ANNOTATION);
                likesRecordMapper.deleteRecord(likesRecord);
            }
            annotationMapper.updateAnnotationByObject(annotation);
            status.put("status","success");
            status.put("data",annotation);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("status",null);
        }
        return (JSONObject) JSON.toJSON(status);
    }
    @PostMapping("delAnnotation")
    public JSONObject delAnnotation(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSON.parseObject(data);
            annotationMapper.deleteAnnotationById(jsonObject.getIntValue("annotationId"));
            likesRecordMapper.deleteRecords(jsonObject.getIntValue("annotationId"),LikesRecord.ANNOTATION);
            status.put("status","success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
        }
        return (JSONObject) JSON.toJSON(status);
    }
    @PostMapping("selectAnnotation")
    public JSONObject selectAnnotation(@RequestBody String data)//data need type:string moduleName:string(not must) openId:string
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSON.parseObject(data);
            List<Annotation> annotations;
            var annUsers = new ArrayList<Data<Annotation>>();
            if(jsonObject.getString("type").equals("all"))
                annotations = annotationMapper.selectAllAnnotation();
            else
                annotations = annotationMapper.selectAnnotationBy(jsonObject.getString("moduleName"));
            for(var i:annotations)
            {
                annUsers.add(new Data<>(i, userMapper.selectUserByOpenId(i.getUserId())));
            }
            var openId= jsonObject.getString("openId");
            List<Integer> likesLikesList = new ArrayList<>();
            for(int i = 0;i<annUsers.size();i++)
            {
                LikesRecord likesRecord = new LikesRecord(openId,annUsers.get(i).getData().getAnnotationId(),LikesRecord.ANNOTATION);
                if(likesRecordMapper.recordIsExists(likesRecord))likesLikesList.add(Integer.valueOf(1));//点赞
                else likesLikesList.add(Integer.valueOf(0));//未点赞
            }
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(annUsers));
            status.put("status","success");
            status.put("data",array);
            status.put("likeRecord",likesLikesList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("status","error");
            status.put("status",null);
        }
        return (JSONObject) JSON.toJSON(status);
    }
}
