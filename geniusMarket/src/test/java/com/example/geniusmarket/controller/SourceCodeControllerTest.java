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
class SourceCodeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Transactional
    @Rollback(value = false)
    void readCode() throws Exception{
        String json ="{\"path\": \"src\\\\\\\\compiler\\\\\\\\codeframe.js\"}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/readCode").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void locationCode() throws Exception{
        String json ="{\"codeId\": 10199}";
        System.out.println(json);
        var res=mockMvc.perform(MockMvcRequestBuilders.post("/locationCode").content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
        res.andExpect(MockMvcResultMatchers.status().isOk());
        res.andDo(MockMvcResultHandlers.print());
    }
}