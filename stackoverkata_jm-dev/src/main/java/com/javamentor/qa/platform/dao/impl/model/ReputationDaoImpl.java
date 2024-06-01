package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReputationDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class ReputationDaoImpl extends ReadWriteDaoImpl<Reputation, Long> implements ReputationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Reputation> getReputationByAnswerIdAndUserId(Long answerId, Long userId) {
        TypedQuery<Reputation> query = (TypedQuery<Reputation>) entityManager
                .createQuery("""
                        SELECT r 
                        FROM Reputation r 
                        WHERE r.answer.id = :answerId 
                        AND r.sender.id = :userId
                        """)
                .setParameter("answerId", answerId)
                .setParameter("userId", userId);
        return SingleResultUtil.getSingleResultOrNull(query);
    }


    @Override
    public Optional<Reputation> getUserReputationById(Long answerId, Long userId) {
        TypedQuery<Reputation> query = (TypedQuery<Reputation>) entityManager
                .createQuery("""
                        SELECT r 
                        FROM Reputation r 
                        WHERE r.answer.id = :answerId 
                        AND r.sender.id = :userId
                        """)
                .setParameter("answerId", answerId)
                .setParameter("userId", userId);
        return SingleResultUtil.getSingleResultOrNull(query);
    }

}
