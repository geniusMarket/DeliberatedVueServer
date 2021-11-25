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
class AnnotationReplyControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Transactional
    @Rollback
    void addAnnotationReply() throws Exception {
        String json = "{\"userId\": \"dhashdjdsad\",\n" +
                "\"detail\": \"abababababab\",\n" +
                "\"annotationId\": 114514}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addAnnotationReply").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void delAnnotationReply() throws Exception{
        String json = "{\"annotationReplyId\": 10018}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/delAnnotationReply").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void anoRepLikes() throws Exception{
        String json = "{\"replyId\": 10021}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/annotationReplyLikes").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void changeAnoRpl() throws  Exception {
        String json = "{\"detail\": \"具体内容\", \n" +
                "\"userId\": \"safsfsdsad\",\n" +
                "\"replyId\": 10021}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/changeAnnotationReply").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void select() throws Exception{
        String json = "{\"annotationId\": 10002}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/selectAnnotationReply").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }
}