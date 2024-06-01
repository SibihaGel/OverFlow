package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDao;
import com.javamentor.qa.platform.models.dto.AnswerDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AnswerDtoDaoImpl implements AnswerDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AnswerDto> getAllAnswersDtoByQuestionId(Long questionId, Long userId) {
        return entityManager.createQuery("""
                        SELECT new com.javamentor.qa.platform.models.dto.AnswerDto (
                            ans.id,
                            ans.question.id,
                            ans.user.id,
                            ans.htmlBody,
                            ans.persistDateTime,
                            ans.isHelpful,
                            ans.dateAcceptTime,
                            (
                            SELECT COUNT(v.id) FROM VoteAnswer v WHERE v.answer.id = ans.id
                            ),
                            (
                            SELECT COALESCE(SUM(r.count), 0) FROM Reputation r WHERE r.author.id = ans.user.id
                            ),
                            ans.user.imageLink,
                            ans.user.nickname,
                            (
                            SELECT SUM(case WHEN (v.voteType = 'UP') THEN 1 WHEN (v.voteType = 'DOWN') THEN -1 else 0 end) FROM VoteAnswer v WHERE v.answer.id = ans.id
                            ),
                            (
                            SELECT COALESCE(v.voteType, 'null') FROM VoteAnswer v WHERE v.user.id = :userId AND v.answer.id = ans.id
                            )
                        )
                        FROM Answer ans WHERE ans.question.id = :questionId AND ans.isDeleted = false
                        """, AnswerDto.class)
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
                .getResultList();
    }

}