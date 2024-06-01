package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Schema(description = "Данные пользователя для регистрации")
public class UserRegistrationDto {

    @Schema(description = "Имя пользователя")
    @NotEmpty
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    @NotEmpty
    private String lastName;

    @Schema(description = "Электронная почта")
    @NotEmpty
    private String email;

    @Schema(description = "Пароль")
    @NotEmpty
    private String password;

}
