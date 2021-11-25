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
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Transactional
    @Rollback
    void myHistory() throws Exception{
        String json ="{\"openId\":\"attention_123\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/myHistory").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void addHistory() throws Exception{
        String json ="{\"openId\":\"attention_123\",\n" +
                "\"questionId\": 10006}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addHistory").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void myQuestion() throws Exception{
        String json ="{\"openId\":\"15sad5as\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/myQuestion").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void myFavorite() throws Exception {
        String json ="{\"openId\":\"attention_123\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/myFavorite").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void addFavorite() throws Exception{

        String json ="{\"openId\":\"sdfsadsad\",\n" +
                "\"questionId\":10001\n," +
                "\"type\": 2\n}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/dealFavorite").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void myFans() throws Exception{
        String json ="{\"openId\":\"oVD6S5fPKU-W7Se0QvYXMZQnhA8Q\",\n" +
                "\"type\": 2 }";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/fans").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void addFans()throws Exception{
        String json ="{\"fansId\":\"Sasafasdas\",\n" +
                "\"attentionId\": \"ASfsfddsaas\",\n" +
                "\"type\": 1}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/dealFans").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void addScore() throws Exception{
        String json ="{\"openId\":\"attention_123\",\n" +
                "\"reward\": 10}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addScore").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }
}