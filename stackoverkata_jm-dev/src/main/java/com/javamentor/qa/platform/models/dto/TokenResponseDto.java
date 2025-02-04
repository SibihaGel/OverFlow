package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO ответа с токеном после успешной аутентификации")
public class TokenResponseDto {
    @NotEmpty
    @Schema(description = "роль пользователя")
    private String role;
    @NotEmpty
    @Schema(description = "JWT токен пользователя")
    private String token;
}
