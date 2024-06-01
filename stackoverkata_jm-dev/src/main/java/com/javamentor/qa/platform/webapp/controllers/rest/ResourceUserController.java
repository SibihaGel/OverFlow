package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "User", description = "The User API")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ResourceUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceUserController.class);

    private final UserDtoService userDtoService;



    @Operation(summary = "Get UserDto by User ID", tags = "user")
    @ApiResponse(responseCode = "200", description = "успешное выполнение",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "404", description = "пользователь не найден")
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable("id")
                                                  @Parameter(name = "id", description = "User id") Long id) {
        LOGGER.info(" \nНайден пользователь с id - {}", id);
        return userDtoService.getUserById(id)
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new UserDto(), HttpStatus.NOT_FOUND));
    }


    @GetMapping("/users")
    public List<User> getAllUsers( ) {
        return userDtoService.getAllUsers();
    }
}
