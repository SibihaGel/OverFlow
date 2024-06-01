package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.dto.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.repository.ReadWriteService;

public interface QuestionService extends ReadWriteService<Question, Long> {
    void saveQuestion(QuestionCreateDto questionCreateDto, User user);
}
