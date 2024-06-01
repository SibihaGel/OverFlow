package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.QuestionCreateDto;

public interface TagDtoService {
    void processTags(QuestionCreateDto questionCreateDto);
}
