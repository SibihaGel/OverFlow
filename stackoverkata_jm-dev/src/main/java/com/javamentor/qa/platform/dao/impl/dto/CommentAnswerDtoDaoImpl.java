package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentAnswerDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.CommentAnswerDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CommentAnswerDtoDaoImpl implements CommentAnswerDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CommentAnswerDto> getById(Long id) {
        return SingleResultUtil.getSingleResultOrNull(
                entityManager.createQuery("""
                    select new com.javamentor.qa.platform.models.dto.CommentAnswerDto(
                        ca.comment.id,
                        ca.answer.id,
                        ca.comment.lastUpdateDateTime,
                        ca.comment.persistDateTime,
                        ca.comment.text,
                        ca.comment.user.id,
                        ca.comment.user.imageLink,
                        (
                            select coalesce(sum(r.count), 0)
                            from Reputation r
                            where r.author.id = ca.comment.user.id
                        )
                    )
                    from CommentAnswer ca where ca.id=:id
                    """, CommentAnswerDto.class).setParameter("id", id));
    }
}
