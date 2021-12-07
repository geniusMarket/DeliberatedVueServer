package com.example.geniusmarket;

import com.alibaba.fastjson.JSONObject;
import com.example.geniusmarket.dao.*;
import com.example.geniusmarket.pojo.LikesRecord;
import com.example.geniusmarket.utils.Dictionary;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
	@Autowired
	LikesRecordMapper likesRecordMapper;
	@Test
	@Rollback
	void contextLoads() {
		System.out.println(questionMapper.selectQuestionById(10052));
	}
}

