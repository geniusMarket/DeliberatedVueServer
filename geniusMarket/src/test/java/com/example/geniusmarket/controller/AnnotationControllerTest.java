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

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeniusMarketApplication.class)
class AnnotationControllerTest {

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
    void addAnnotation() throws Exception{
        String json = "{\"userId\":\"DSAsddad\",\n" +
                "\"filePath\": \"src\\\\compiler\\\\codeframe.js\" ,\n" +
                "\"moduleName\": \"src\\\\compiler\\\\codeframe.js\",\n" +
                "\"detail\": \"具体内容\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/addAnnotation").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Transactional
    @Rollback
    void changeAnnotation() throws Exception{
        String json = "{\"detail\": \"修改后容\",\n" +
                "\"annotationId\": 10033}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/changeAnnotation").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void annotationLikes() throws Exception{
        String json = "{\"detail\": \"修改后容\",\n" +
                "\"annotationId\": 10033}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/changeAnnotation").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void delAnnotation() throws Exception{
        String json = "{\"annotationId\": 10001}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/delAnnotation").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback
    void selectAnnotation() throws Exception{
        String json = "{\"moduleName\": \"src\\\\\\\\asfsad\\\\\\\\safsadsa\\\\\\\\dfdasds.js\",\n" +
                "\"type\": \"all\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/selectAnnotation").content(json.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }
}