package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.exception.AnswerException;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentAnswerServiceImpl extends ReadWriteServiceImpl<CommentAnswer, Long> implements CommentAnswerService {

    private final AnswerService answerService;

    public CommentAnswerServiceImpl(ReadWriteDao<CommentAnswer, Long> readWriteDao, AnswerService answerService) {
        super(readWriteDao);
        this.answerService = answerService;
    }

    @Override
    @Transactional
    public Long persistAndReturnId(User user, Long answerId, String text) {
        CommentAnswer commentAnswer = new CommentAnswer();
        commentAnswer.setAnswer(answerService.getById(answerId)
                .orElseThrow(() -> new AnswerException("Ответа не существует")));
        commentAnswer.setText(text);
        commentAnswer.setUser(user);
        persist(commentAnswer);
        return commentAnswer.getId();
    }
}
