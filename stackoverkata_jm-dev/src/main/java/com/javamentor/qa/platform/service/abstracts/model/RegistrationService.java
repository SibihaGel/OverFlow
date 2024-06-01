package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.dto.UserRegistrationDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.repository.ReadWriteService;

public interface RegistrationService extends ReadWriteService<User, Long> {

    boolean registerUser(UserRegistrationDto userRegistrationDto);

    boolean confirmEmail(String confirmationToken, String email);


}
