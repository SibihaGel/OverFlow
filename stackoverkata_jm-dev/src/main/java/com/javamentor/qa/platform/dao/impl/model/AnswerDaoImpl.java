package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class AnswerDaoImpl extends ReadWriteDaoImpl<Answer, Long> implements AnswerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Answer> getAnswerById(Long answerId, Long userId) {
        TypedQuery<Answer> query = (TypedQuery<Answer>) entityManager
                .createQuery("""
                        SELECT a 
                        FROM Answer a 
                        WHERE a.id = :answerId 
                        AND NOT a.user.id = :userId
                        """)
                .setParameter("answerId", answerId)
                .setParameter("userId", userId);
        return SingleResultUtil.getSingleResultOrNull(query);
    }

    @Override
    @Transactional
    public void markAnswerToDelete(Long answerId) {
        entityManager.createQuery("""
                UPDATE Answer a
                SET a.isDeleted = true
                WHERE a.id = :answerId
                """)
                .setParameter("answerId", answerId)
                .executeUpdate();
    }

}