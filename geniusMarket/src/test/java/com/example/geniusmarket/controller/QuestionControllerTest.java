package com.example.geniusmarket.controller;

import com.example.geniusmarket.GeniusMarketApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeniusMarketApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Transactional
    @Rollback
    void addQuestion() throws Exception{
        String json ="{\"openId\":\"15sad5as\",\n" +
                "\"title\": \"测试的标题\",\n" +
                "\"detail\": \"测试的内容\",\n" +
                "\"codeId\": 10001,\n" +
                "\"reward\": 10}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addQuestion").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void delQuestion() throws Exception{
        String json ="{\"questionId\": 10001}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/delQuestion").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void changeQuestion() throws Exception{
        String json ="{\"detail\": \"修该后的具体内容\", \n" +
                "\"title\": \"修改后的标题\",\n" +
                "\"questionId\":10002,\n" +
                "\"reward\": 100}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/changeQuestion").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void selectQuestion() throws Exception{
        String json ="{\"type\": 3,\n" +
                "\"detail\": \"dasd\", \"codeId\":10001}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/selectQuestion").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Transactional
    @Rollback
    void questionIsAccept() throws Exception{
        String json ="{questionId: 10052}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/getQuestionIsAccept").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }
}