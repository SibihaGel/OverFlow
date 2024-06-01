package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteAnswerDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class VoteAnswerDaoImpl extends ReadWriteDaoImpl<VoteAnswer, Long> implements VoteAnswerDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Long getAllTheVotesForThisAnswer(Long answerId) {
        Query query = entityManager
                .createQuery("""
                SELECT COUNT (v.voteType) AS count
                FROM VoteAnswer v  
                WHERE v.answer.id = :id
                """, VoteAnswer.class)
                .setParameter("id",answerId);
        return (Long) query.getSingleResult();
    }

    @Override
    public Optional<VoteAnswer> getVoteAnswerById(Long answerId, Long userId) {
        TypedQuery<VoteAnswer> query = (TypedQuery<VoteAnswer>) entityManager
                .createQuery("""
                        FROM VoteAnswer v 
                        WHERE v.answer.id = :answerId 
                        AND v.user.id = :userId
                        """)
                .setParameter("answerId", answerId)
                .setParameter("userId", userId);
        return SingleResultUtil.getSingleResultOrNull(query);
    }


    @Override
    public long countAnswerVotes(Long answerId) {
        return entityManager
                .createQuery("""
                                SELECT COUNT(v) 
                                FROM VoteAnswer v 
                                WHERE v.answer.id = :answerId
                                """,
                        Long.class)
                .setParameter("answerId", answerId)
                .getSingleResult();
    }

    @Override
    public Optional<VoteAnswer> getVoteAnswerByAnswerIdAndUserId(Long answerId, Long userId) {

        return SingleResultUtil.getSingleResultOrNull(
                entityManager.createQuery("""
                        from VoteAnswer v
                        where v.answer.id = : answerId 
                        and v.user.id = : userId 
                        and v.voteType = 'UP' 
                        """, VoteAnswer.class)
                        .setParameter("answerId", answerId)
                        .setParameter("userId", userId));
    }
}
