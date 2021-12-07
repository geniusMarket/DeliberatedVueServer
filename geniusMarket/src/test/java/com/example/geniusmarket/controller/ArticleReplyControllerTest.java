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
class ArticleReplyControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Transactional
    @Rollback
    void addReply() throws Exception{
        String json ="{\"replier\":\"DSAsddad\",\n" +
                "\"articleId\": 10001,\n" +
                "\"detail\": \"具体内容\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addArticleReply").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void articleReplyLikes() throws Exception{
        String json ="{\"articleReplyId\": 10014,\"openId\": \"fans_123\",\n" +
                "\"type\": 1}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/articleReplyLikes").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void delReply() throws Exception{
        String json ="{\"articleReplyId\": 10001}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/delArticleReply").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void replies() throws Exception{
        String json ="{\"articleId\":10023}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/articleReplies").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

}