package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.converters.UserConverter;
import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.dao.abstracts.repository.UserRepository;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    private final UserRepository userRepository;
    private final UserDtoDao userDtoDao;
@Autowired
    public UserDtoServiceImpl(UserRepository userRepository, UserDtoDao userDtoDao) {
        this.userRepository = userRepository;
        this.userDtoDao = userDtoDao;
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return userDtoDao.getById(id);
    }

    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }
}