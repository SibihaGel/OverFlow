package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.repository.ReadWriteService;

public interface CommentAnswerService extends ReadWriteService<CommentAnswer, Long> {

    Long persistAndReturnId(User user, Long answerId, String text);
}
