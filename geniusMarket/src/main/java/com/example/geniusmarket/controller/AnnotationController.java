package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.AnnotationMapper;
import com.example.geniusmarket.dao.UserMapper;
import com.example.geniusmarket.pojo.Annotation;
import com.example.geniusmarket.utils.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class AnnotationController {
    @Autowired
    AnnotationMapper annotationMapper;
    @Autowired
    UserMapper userMapper;
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
    @PostMapping("annotationLikes")
    public JSONObject annotationLikes(@RequestBody String data)
    {
        var status = new HashMap<String,Object>();
        try
        {
            JSONObject jsonObject = JSON.parseObject(data);
            var annotation = annotationMapper.selectAnnotationById(jsonObject.getIntValue("annotationId"));
            annotation.setLikes(annotation.getLikes()+1);
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
    public JSONObject selectAnnotation(@RequestBody String data)
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
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(annUsers));
            status.put("status","success");
            status.put("data",array);
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
