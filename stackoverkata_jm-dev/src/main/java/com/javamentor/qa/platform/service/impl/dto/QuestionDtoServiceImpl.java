package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.mappers.QuestionMapper;
import com.javamentor.qa.platform.models.dto.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {

    private final UserService userService;
    private final QuestionMapper questionMapper;

    public QuestionDtoServiceImpl(UserService userService, QuestionMapper questionMapper) {
        this.userService = userService;
        this.questionMapper = questionMapper;
    }

    public QuestionDto convertToQuestionDto(QuestionCreateDto questionCreateDto) {

        QuestionDto questionDto = questionMapper
                .questionCreatDtoToQuestionDto(questionCreateDto);

        enrichQuestionDto(questionDto);

        return questionDto;
    }
    private void enrichQuestionDto(QuestionDto questionDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        questionDto.setPersistDateTime(LocalDateTime.now());
        questionDto.setLastUpdateDateTime(LocalDateTime.now());
        questionDto.setAuthorName(user.getUsername());
        questionDto.setAuthorImage(user.getImageLink());
        questionDto.setAuthorId(user.getId());
    }
}