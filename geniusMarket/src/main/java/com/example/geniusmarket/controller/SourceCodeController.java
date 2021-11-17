package com.example.geniusmarket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.SourceCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SourceCodeController {
    @Autowired
    SourceCodeMapper sourceCodeMapper;
    @PostMapping("/readCode")
    public JSONObject readCode(@RequestBody String data)
    {
        var status =new HashMap<String,Object>();
        status.put("status","success");
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var code = sourceCodeMapper.selectSourceCodeByPath(jsonObject.getString("path"));
            status.put("data",code);
            return (JSONObject) JSONObject.toJSON(status);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("data",null);
            status.replace("status","error");
            return (JSONObject) JSONObject.toJSON(status);
        }

}
    @PostMapping("locationCode")
    public JSONObject locationCode(@RequestBody String data)
    {
        var status =new HashMap<String,Object>();
        status.put("status","success");
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            var code = sourceCodeMapper.selectSourceCodeById(jsonObject.getIntValue("codeId"));
            status.put("data",code);
            return (JSONObject) JSON.toJSON(status);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status.put("data",null);
            status.replace("status","error");
            return (JSONObject) JSON.toJSON(status);
        }
    }
}
