package com.javamentor.qa.platform.mappers;

import com.javamentor.qa.platform.models.dto.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface QuestionMapper {
    Question questionCreatDtoToQuestion(QuestionCreateDto questionCreateDto);
    @Mapping( source = "tags", target = "listTagDto")
    QuestionDto questionCreatDtoToQuestionDto(QuestionCreateDto questionCreateDto);
    @Mapping(source = "listTagDto", target = "tags")
    @Mapping(source = "authorId", target = "user.id")
    @Mapping(source = "authorName", target = "user.fullName")
    Question questionDtoToQuestion(QuestionDto questionDto);

    @InheritInverseConfiguration
    QuestionDto questionToQuestionto(Question question);



}




