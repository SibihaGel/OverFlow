package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.transaction.annotation.Transactional;

public interface TagDtoDao {

    Long saveTag(TagDto tagDto);
}

