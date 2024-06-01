package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.mappers.QuestionMapper;
import com.javamentor.qa.platform.models.dto.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class QuestionServiceImpl extends ReadWriteServiceImpl<Question, Long> implements QuestionService {

    @Autowired
    public QuestionService self;
    private final QuestionMapper questionMapper;
    private final QuestionDtoDao questionDtoDao;

    @Autowired
    private ApplicationContext context;

    public QuestionServiceImpl(ReadWriteDao<Question, Long> readWriteDao, QuestionMapper questionMapper, QuestionDtoDao questionDtoDao) {
        super(readWriteDao);
        this.questionMapper = questionMapper;
        this.questionDtoDao = questionDtoDao;

    }

    @PostConstruct
    public void printContext(){
        System.out.println(context.getBeanDefinitionCount());
    }


    @PostConstruct
    public void init(){
        System.out.println(self.getClass());
    }
    @Transactional
    public void saveQuestion (QuestionCreateDto questionCreateDto, User user) {

        Question question = questionMapper.questionCreatDtoToQuestion(questionCreateDto);
        question.setUser(user);
        question.setIsDeleted(false);
        question.setPersistDateTime(LocalDateTime.now());
        question.setLastUpdateDateTime(LocalDateTime.now());
        persist(question);
    }
}
