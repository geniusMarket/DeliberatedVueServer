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

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeniusMarketApplication.class)
class AnswerReplyControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @Transactional
    @Rollback
    void addAnswerReply() throws Exception{
        String json = "{\"answerId\": 10004,\n" +
                "\"openId\":\"1d5asdas\",\n" +
                "\"detail\":\"dasdfasfdfadas\",\n" +
                "\"reward\": 10}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addAnswerReply").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void delAnswerRely() throws Exception{
        String json = "{\"answerReplyId\": 1}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/delAnswerReply").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void changeAnsRpy() throws Exception{
//        String json = "{\"answerReplyId\": 1}";
//        System.out.println(json);
//        var res=mockMvc.perform(MockMvcRequestBuilders.post("/delAnswerReply").content(json.getBytes(StandardCharsets.UTF_8))
//                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
//        res.andExpect(MockMvcResultMatchers.status().isOk());
//        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void addAPL() throws Exception{
        String json = "{\"answerReplyId\": 3}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addAnswerReplyLikes").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void selectAPL() throws Exception{
        String json = "{\"answerId\": 10004}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/selectAnswerReply").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }
}