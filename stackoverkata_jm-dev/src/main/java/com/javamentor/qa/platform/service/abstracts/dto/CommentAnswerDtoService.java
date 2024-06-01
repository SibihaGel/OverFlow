package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.CommentAnswerDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.Optional;

public interface CommentAnswerDtoService {

    CommentAnswerDto persistAndReturnDto(User user, Long answerId, String text);

    Optional<CommentAnswerDto> getById(Long id);
}
