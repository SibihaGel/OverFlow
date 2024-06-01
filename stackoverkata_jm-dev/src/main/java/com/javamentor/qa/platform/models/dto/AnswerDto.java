package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.entity.question.VoteType;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    @Parameter(description = "id ответа на вопрос")
    private Long id;

    @Schema(description = "id пользователя")
    private Long userId;

    @Schema(description = "id вопроса")
    private Long questionId;

    @Schema(description = "текст ответа")
    @NotEmpty

    @NotBlank
    @NotNull
    private String body;

    @Schema(description = "дата создания ответа")
    private LocalDateTime persistDate;

    @Schema(description = "польза ответа")
    private Boolean isHelpful;

    @Schema(description = "дата решения вопроса")
    private LocalDateTime dateAccept;

    @Schema(description = "рейтинг ответа")
    private Long countValuable;

    @Schema(description = "рейтинг юзера")
    private Long countUserReputation;

    @Schema(description = "ссылка на картинку пользователя")
    private String image;

    @Schema(description = "никнейм пользователя")
    private String nickname;

    @Schema(description = "Количество голосов")
    private Long countVote;

    @Schema(description = "тип голоса")
    private VoteType voteType;

    //TODO поле не обрабатывается на стороне фронта, удалить
    @Schema(description = "пометка на удаление")
    private boolean isDeleted; // Поле для пометки на удаление

}
