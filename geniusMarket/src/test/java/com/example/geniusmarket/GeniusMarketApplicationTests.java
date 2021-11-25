package com.example.geniusmarket;

import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.*;
import com.example.geniusmarket.pojo.*;
import com.example.geniusmarket.utils.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class GeniusMarketApplicationTests {
	@Autowired
	AnnotationMapper annotationMapper;
	@Autowired
	AnnotationReplyMapper annotationReplyMapper;
	@Autowired
	AnswerMapper answerMapper;
	@Autowired
	AnswerReplyMapper answerReplyMapper;
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	ArticleReplyMapper articleReplyMapper;
	@Autowired
	FansMapper fansMapper;
	@Autowired
	FavoriteMapper favoriteMapper;
	@Autowired
	HistoryMapper historyMapper;
	@Autowired
	QuestionMapper questionMapper;
	@Autowired
	SourceCodeMapper sourceCodeMapper;
	@Autowired
	UserMapper userMapper;
	@Test
	void contextLoads() {
		Data<Integer>data = new Data<>(1,new User());
		System.out.println(JSONObject.toJSON(data));
	}
}

