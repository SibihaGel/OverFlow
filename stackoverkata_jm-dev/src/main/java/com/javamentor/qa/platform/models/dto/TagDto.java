package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "тэг")
public class TagDto {
    @Parameter(description = "id тэга")
    private Long id;
    @Schema(description = "название тэга")
    private String name;
    @Schema(description = "описание тэга")
    private String description;
}
