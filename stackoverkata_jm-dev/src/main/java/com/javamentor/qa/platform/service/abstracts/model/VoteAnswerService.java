package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.repository.ReadWriteService;

public interface VoteAnswerService extends ReadWriteService<VoteAnswer, Long> {

    void voteUpToAnswer(User user, Answer answer);

    Long getAllTheVotesForThisAnswer(Long id);

    void voteDown(User user, Long answerID, Answer answer);

    Long countAnswerVotes(Long answerId);


}
