package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDao;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TagDtoDaoImpl implements TagDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Long saveTag(TagDto tagDto) {

        Tag newtag = new Tag();
        newtag.setName(tagDto.getName());
        newtag.setDescription(tagDto.getDescription());
        entityManager.persist(newtag);
        return newtag.getId();}
}
