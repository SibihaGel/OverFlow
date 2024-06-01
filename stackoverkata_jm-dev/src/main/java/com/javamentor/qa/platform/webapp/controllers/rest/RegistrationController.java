package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.UserRegistrationDto;
import com.javamentor.qa.platform.service.abstracts.model.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User Registration", description = "The Registration API")
@RequestMapping("api/user/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @Operation(summary = "Send email with verification link", tags = "registration")
    @ApiResponse(responseCode = "200", description = "успешное выполнение")
    @PostMapping()
    public ResponseEntity<String> sendMessage(@RequestBody UserRegistrationDto userRegistrationDto) {

        if (registrationService.registerUser(userRegistrationDto)) {
            return ResponseEntity.ok().body("Verify email by the link sent on your email address");
        } else {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
    }

    @Operation(summary = "Verification user's account", tags = "registration")
    @ApiResponse(responseCode = "200", description = "успешное выполнение")
    @ApiResponse(responseCode = "400", description = "параметр не найден")
    @GetMapping("verify")
    public ResponseEntity<String> verify(@RequestParam("token")
                                         @Parameter(name = "token", description = "Verification token") String confirmationToken,
                                         @RequestParam("email")
                                         @Parameter(name = "id", description = "User email") String email) {
        if (registrationService.confirmEmail(confirmationToken, email)) {
            return ResponseEntity.ok().body("Email verified successfully!");
        } else {
            return ResponseEntity.badRequest().body("Error: Couldn't verify email");
        }
    }


}
