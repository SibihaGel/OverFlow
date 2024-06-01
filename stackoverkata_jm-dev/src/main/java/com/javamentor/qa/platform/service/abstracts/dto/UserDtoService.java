package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDtoService {
    Optional<UserDto> getUserById(Long id);

    List<User> getAllUsers();
}
