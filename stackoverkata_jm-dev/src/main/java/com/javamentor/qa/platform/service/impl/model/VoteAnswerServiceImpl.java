package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteAnswerDao;
import com.javamentor.qa.platform.models.entity.question.VoteType;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import com.javamentor.qa.platform.service.abstracts.model.VoteAnswerService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class VoteAnswerServiceImpl extends ReadWriteServiceImpl<VoteAnswer, Long> implements VoteAnswerService {
    
    public final VoteAnswerDao voteAnswerDao;
    public final ReputationService reputationService;
    public final AnswerService answerService;

    public VoteAnswerServiceImpl(VoteAnswerDao voteAnswerDao, ReputationService reputationService, AnswerService answerService) {
        super(voteAnswerDao);
        this.voteAnswerDao = voteAnswerDao;
        this.reputationService = reputationService;
        this.answerService = answerService;
    }
        @Override
        @Transactional
        public void voteUpToAnswer (User userWhoVotes, Answer answer){

            Optional<VoteAnswer> optionalVoteAnswer =
                    voteAnswerDao.getVoteAnswerByAnswerIdAndUserId(answer.getId(), userWhoVotes.getId());

            if (optionalVoteAnswer.isEmpty()) {
                voteAnswerDao.persist(new VoteAnswer(userWhoVotes, answer, VoteType.UP));
            } else {
                voteAnswerDao.update(optionalVoteAnswer.get());
            }
        }

        @Override
        public Long getAllTheVotesForThisAnswer (Long answerId){
            return voteAnswerDao.getAllTheVotesForThisAnswer(answerId);

        }

        @Override
        public Long countAnswerVotes (Long answerId){
            return voteAnswerDao.countAnswerVotes(answerId);
        }

        @Override
        public void voteDown (User user, Long answerID, Answer answer){
            Optional<VoteAnswer> voteAnswerOptional = voteAnswerDao.getVoteAnswerById(answerID, user.getId());
            if (voteAnswerOptional.isEmpty()) {
                reputationService.downReputation(answer, user);
                voteAnswerDao.persist(new VoteAnswer(user, answer, VoteType.DOWN));
            }
            voteAnswerDao.countAnswerVotes(answerID);
        }
    }

