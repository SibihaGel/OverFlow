package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "комментарий ответа")
public class CommentAnswerDto {

    @Parameter(description = "id комментария")
    private Long id;

    @Parameter(description = "id ответа")
    private Long answerId;

    @Parameter(description = "дата редактирования")
    private LocalDateTime lastRedactionDate;

    @Parameter(description = "дата создания ответа")
    private LocalDateTime persistDate;

    @NotNull
    @NotEmpty
    @Parameter(description = "текст комментария")
    private String text;

    @Parameter(description = "id пользователя")
    private Long userId;

    @Parameter(description = "ссылка на картинку пользователя")
    private String imageLink;

    @Parameter(description = "репутация пользователя")
    private Long reputation;
}
