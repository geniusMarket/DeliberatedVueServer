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
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnswerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Transactional
    @Rollback
    void addAnswer() throws  Exception{
        String json = "{\"answerer\":\"1d5asdsa\",\n" +
                "\"questionId\": 114514,\n" +
                "\"detail\": \"这是一个回答\",\n" +
                "\"reward\": 10\n}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addAnswer").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void delAnswer() throws Exception{
        String json = "{\"answerId\": 10003}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/delAnswer").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void changeAnswer() throws Exception{
        String json = "{\"detail\": \"修改后的具体内容\", \n" +
                "\"answerId\":10004,\n" +
                "\"reward\": 10}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/changeAnswer").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void addAnswerLikes() throws Exception{
        String json = "{\"answerId\": 10004,\"openId\": \"fans_123\",\n" +
                "\"type\": 1}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addAnswerLikes").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void selectAnswer() throws Exception {
        String json = "{\"questionId\": 114514,\"openId\": \"fans_123\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/selectAnswer").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }
}