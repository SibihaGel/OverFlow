package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentAnswerDtoDao;
import com.javamentor.qa.platform.models.dto.CommentAnswerDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.CommentAnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentAnswerDtoServiceImpl implements CommentAnswerDtoService {

    private final CommentAnswerService commentAnswerService;
    private final CommentAnswerDtoDao commentAnswerDtoDao;

    public CommentAnswerDtoServiceImpl(CommentAnswerService commentAnswerService, CommentAnswerDtoDao commentAnswerDtoDao) {
        this.commentAnswerService = commentAnswerService;
        this.commentAnswerDtoDao = commentAnswerDtoDao;
    }

    @Override
    public CommentAnswerDto persistAndReturnDto(User user, Long answerId, String text) {
        return getById(commentAnswerService.persistAndReturnId(user, answerId, text)).orElseThrow();
    }

    @Override
    public Optional<CommentAnswerDto> getById(Long id) {
        return commentAnswerDtoDao.getById(id);
    }
}
