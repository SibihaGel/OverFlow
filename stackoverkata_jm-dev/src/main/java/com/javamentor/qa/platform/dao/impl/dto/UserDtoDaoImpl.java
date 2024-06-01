package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDtoDaoImpl implements UserDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<UserDto> getById(Long id) {

        return SingleResultUtil.getSingleResultOrNull(
                entityManager.createQuery("""
                        SELECT new com.javamentor.qa.platform.models.dto.UserDto (
                        u.id,
                        u.email,
                        u.fullName,
                        u.imageLink,
                        u.city,
                        (select coalesce(sum(r.count)) 
                            from Reputation r 
                            where r.author.id=:id),
                        u.persistDateTime,
                        ( select count(distinct va.id) + count(distinct vq.id)
                                from VoteAnswer va, VoteQuestion vq 
                                where va.user.id=vq.user.id 
                                and va.user.id=:id 
                                and vq.user.id=:id)
                        ) FROM User u where u.id=:id
                        """, UserDto.class)
                        .setParameter("id", id));
    }


}