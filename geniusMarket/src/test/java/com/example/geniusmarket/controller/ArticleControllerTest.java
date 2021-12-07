package com.example.geniusmarket.controller;

import com.example.geniusmarket.GeniusMarketApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class ArticleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    @Rollback
    void addArticle() throws Exception{
        String json = "{\"author\":\"DSAsddad\",\n" +
                "\"title\": \"sdsa\",\n" +
                "\"detail\": \"具体内容\", \n" +
                "\"codeId\": 10001,\n" +
                "\"type\": 1}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addArticle").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void articleLikes() throws Exception{
        String json = "{\"articleId\": 10001，\n" +
                "\"openId\": \"fans_123\",\n" +
                "\"type\": 1}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/articleLikes").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void articles() throws Exception{
        String json = "{\"type\": \"all\",\n" +
                "\"request\": \"asdsad\",\"openId\": \"fans_123\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/selectArticles").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void delArticle() throws Exception{
        String json = "{\"articleId\": 10001}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/delArticle").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void getArticle() throws Exception{
        String json = "{\"articleId\": 10023,\"openId\": \"fans_123\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/getArticle").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }
}