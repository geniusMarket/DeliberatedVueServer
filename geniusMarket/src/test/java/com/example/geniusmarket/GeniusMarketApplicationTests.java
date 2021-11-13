package com.example.geniusmarket;

import com.example.geniusmarket.dao.*;
import com.example.geniusmarket.pojo.Annotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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
	
	}
}
