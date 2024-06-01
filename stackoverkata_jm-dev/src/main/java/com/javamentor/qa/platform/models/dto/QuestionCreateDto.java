package com.javamentor.qa.platform.models.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Cоздание вопроса")
public class QuestionCreateDto {

    @NotNull (message = "Зоголовок должен быть заполнен")
    @Schema(description = "Заголовок создаваемого вопроса")
    private String title;
    @NotNull (message = "Описание должно сбыть заполнено")
    @Schema(description = "Описание создаваемого вопроса")
    private String description;
    @NotNull (message = "Теги должны быть заполнены")
    @Schema(description = "Теги создаваемого вопроса")
    private List<TagDto> tags;
}
