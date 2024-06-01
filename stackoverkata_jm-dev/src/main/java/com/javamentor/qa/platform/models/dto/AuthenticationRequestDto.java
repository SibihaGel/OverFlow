package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для аутентификации")
public class AuthenticationRequestDto {
    @NotBlank
    @Schema(description = "логин пользователя")
    private String login;
    @NotBlank
    @Schema(description = "пароль пользователя")
    private String pass;

}
