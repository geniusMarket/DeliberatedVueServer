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
class CurrencyControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    @Transactional
    @Rollback(value = false)
    void del() {
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void addLike() {
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void verify()
    {
        try {
            var res = mockMvc.perform(MockMvcRequestBuilders.post("/getVerify")
                    .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE));
            res.andExpect(MockMvcResultMatchers.status().isOk());
            res.andDo(MockMvcResultHandlers.print());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}