package com.javamentor.qa.platform.mappers;

import com.javamentor.qa.platform.models.dto.AnswerDto;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AnswerMapper {

    Answer answerCreatDtoToAnswer(AnswerDto answerDtoDto);
}
