package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "Question", description = "Question API")
@RequestMapping("/api/user/question")
@RequiredArgsConstructor
public class ResourceQuestionController {

    private final QuestionDtoService questionDtoService;
    private final TagDtoService tagDtoService;
    private final QuestionService questionService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceQuestionController.class);

    @Operation(summary = "Добавление вопроса",
            description = "Добавляет вопрос пользователя и теги вопроса")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно возращен вопрос"),
            @ApiResponse(responseCode = "401", description = "Объект не найден")
    })
    @PostMapping()
    public ResponseEntity<QuestionDto> addNewQuestion(@RequestBody @Valid QuestionCreateDto questionCreateDto) {
        if (questionCreateDto == null) {
            LOGGER.warn("Incorrect data...");
            return null;
        }
        tagDtoService.processTags(questionCreateDto);
        LOGGER.info("The new Tags was added...");

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        questionService.saveQuestion(questionCreateDto, user);
        LOGGER.info("You're Question was added..");

        return new ResponseEntity<>(questionDtoService.convertToQuestionDto(questionCreateDto), HttpStatus.OK);


    }
}
