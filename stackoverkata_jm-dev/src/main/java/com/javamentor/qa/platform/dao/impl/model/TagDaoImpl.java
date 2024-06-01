package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.TagDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class TagDaoImpl extends ReadWriteDaoImpl<Tag, Long> implements TagDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Tag> getByName(String name) {
        Query query = entityManager.createQuery("""
                        SELECT t
                        FROM Tag t
                        WHERE t.name =:name
                        """)
                .setParameter("name", name)
                .setMaxResults(1);

        Tag tag = (Tag) query.getSingleResult();
        return Optional.of(tag);
    }
}
