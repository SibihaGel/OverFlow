package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.CommentAnswerDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.AnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.CommentAnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import com.javamentor.qa.platform.service.abstracts.model.VoteAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Answer", description = "Answer API")
@RequestMapping("api/user/question/{questionId}/answer")
@RequiredArgsConstructor
public class ResourceAnswerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceAnswerController.class);

    private final CommentAnswerDtoService commentAnswerDtoService;
    private final AnswerDtoService answerDtoService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final VoteAnswerService voteAnswerService;
    private final ReputationService reputationService;

    @Tag(name = "Comment", description = "Comment API")
    @PostMapping("{answerId}/comment")
    @ApiResponse(responseCode = "200", description = "Добавлен новый коментарий")
    @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    public ResponseEntity<CommentAnswerDto> addCommentToAnswer(
            @PathVariable @Parameter(name = "questionId", description = "Question id") Long questionId,
            @PathVariable @Parameter(name = "answerId", description = "Answer id") Long answerId,
            @RequestBody @Parameter(name = "text", description = "Text") String text,
            @AuthenticationPrincipal User user) {

        if (text.isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Оставлен комментарий к ответу c id - {} на вопрос с id - {} пользователем с id - {}\n",
                answerId, questionId, user.getId());
        return new ResponseEntity<>(commentAnswerDtoService.persistAndReturnDto(user, answerId, text), HttpStatus.OK);
    }

    @Operation(summary = "Получение ответов текущего пользователя", description = "Возвращает List<AnswerDto> по Id вопроса для аутентифицированного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно возращен"),
            @ApiResponse(responseCode = "404", description = "Вопрос с данным Id не найден")
    })
    @GetMapping
    public ResponseEntity<List<AnswerDto>> getAllAnswers(@PathVariable Long questionId, @AuthenticationPrincipal User user) {
        if (questionService.getById(questionId).isEmpty()) {
            LOGGER.warn("Вопрос с questionId - {} не найден\n",
                    questionId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Вернулся список AnswerDTO для вопроса questionId - {} для юзера - {}\n",
                questionId, user);
        return new ResponseEntity<>(answerDtoService.getAllAnswersDtoByQuestionId(questionId, user.getId()), HttpStatus.OK);
    }

    @Operation(summary = "Понижение рейтинга",
            description = "Понижает рейтинг пользователя")
    @ApiResponse(responseCode = "200",
            description = "Возвращает общее количество голосов, понижает рейтинг")
    @ApiResponse(responseCode = "400",
            description = "Ошибка голосования")
    @PostMapping("/{questionId}/answer/{id}/downVote")
    public ResponseEntity<Long> downVoteAnswer(@PathVariable("id") Long answerId, @AuthenticationPrincipal User userAuthenticated) {

        Optional<Answer> answerOptional = answerService.getAnswerById(answerId, userAuthenticated.getId());
        if (answerOptional.isEmpty()) {
            LOGGER.info("Попытка пользователя {} проголосовать за себя",
                    userAuthenticated.getEmail());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Answer answer = answerOptional.get();
        voteAnswerService.voteDown(userAuthenticated, answerId, answer);
        LOGGER.info(String.format("Рейтинг пользователя понижен",
                answer.getUser().getId()));
        return new ResponseEntity<>(voteAnswerService.countAnswerVotes(answer.getId()), HttpStatus.OK);
    }

    @Operation(summary = "Голосование за ответ", description = "Получение общего кол-ва голосов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное голосование"),
            @ApiResponse(responseCode = "404", description = "Ошибка голосования")
    })
    @PostMapping("{questionId}/answer/{id}/upVote")
    public ResponseEntity<Long> upVoteAnswer(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        Optional<Answer> optionalAnswer = answerService.getAnswerById(id, user.getId());

        if (optionalAnswer.isEmpty()) {
            LOGGER.info("Ответ с id - {} не найден \n ",
                    id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Answer answer = optionalAnswer.get();
        voteAnswerService.voteUpToAnswer(user, answer);
        reputationService.addReputation(user, answer);
        LOGGER.info("За ответ с идентификатором {} проголосовал пользователь {}. Общее кол-во голосов: {}",
                answer.getId(), user.getId(), voteAnswerService.getAllTheVotesForThisAnswer(answer.getId()));
        return new ResponseEntity<>(voteAnswerService.getAllTheVotesForThisAnswer(answer.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{answerId}")
    @Operation(summary = "Пометить ответ для удаления", description = "Помечает ответ для удаления и возвращает статус")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ответ помечен для удаления"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Вопрос или ответ не найдены")
    })
    public ResponseEntity<?> markAnswerToDelete(
            @PathVariable("questionId") Long questionId,
            @PathVariable("answerId") Long answerId,
            @AuthenticationPrincipal User user) {

        if (user == null) {
            LOGGER.warn("Пройдите авторизацию!!!\n");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (questionService.getById(questionId).isEmpty()) {
            LOGGER.warn("Вопрос с questionId - {} не найден\n", questionId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        answerService.markAnswerToDelete(answerId);

        LOGGER.info("Ответ {} отмечен для удаления пользователем", answerId);
        return new ResponseEntity<>("Ответ помечен на удаление", HttpStatus.OK);
    }

}
