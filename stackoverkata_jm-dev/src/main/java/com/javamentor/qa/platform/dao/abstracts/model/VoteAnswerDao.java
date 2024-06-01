package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;

import javax.persistence.TypedQuery;
import java.util.Optional;

public interface VoteAnswerDao extends ReadWriteDao<VoteAnswer, Long> {

    Optional<VoteAnswer> getVoteAnswerByAnswerIdAndUserId(Long id, Long id1);

    Long getAllTheVotesForThisAnswer(Long answerId);

    Optional<VoteAnswer> getVoteAnswerById(Long answerID, Long userID);

    long countAnswerVotes(Long answerId);


}
