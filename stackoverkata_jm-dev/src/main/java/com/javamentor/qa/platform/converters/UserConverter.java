package com.javamentor.qa.platform.converters;

import com.javamentor.qa.platform.models.dto.UserRegistrationDto;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper( UserConverter.class );
    @Mapping(target = "fullName", expression = "java(userRegistrationDto.getFirstName() + \" \" + userRegistrationDto.getLastName())")
    User userRegistrationDtoToUser(UserRegistrationDto userRegistrationDto);

    @InheritInverseConfiguration
    UserRegistrationDto userToUserRegistrationDto(User user);
}
