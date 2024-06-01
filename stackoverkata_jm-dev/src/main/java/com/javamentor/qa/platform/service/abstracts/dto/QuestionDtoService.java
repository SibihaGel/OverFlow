package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.QuestionDto;

public interface QuestionDtoService {
    QuestionDto convertToQuestionDto(QuestionCreateDto questionCreateDto);
}
