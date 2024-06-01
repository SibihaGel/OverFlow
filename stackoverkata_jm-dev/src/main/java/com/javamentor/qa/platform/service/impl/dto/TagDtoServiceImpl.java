package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagDtoServiceImpl implements TagDtoService {
    private final TagService tagService;
    private final TagDtoDao tagDtoDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(TagDtoServiceImpl.class);

    public TagDtoServiceImpl(TagService tagService, TagDtoDao tagDtoDao) {
        this.tagService = tagService;
        this.tagDtoDao = tagDtoDao;
    }
    public void processTags(QuestionCreateDto questionCreateDto) {
        for (TagDto tagDto : questionCreateDto.getTags()) {
            String tagName = tagDto.getName();
            Optional<Tag> existingTag = Optional.empty();
            try {
                existingTag = tagService.getByName(tagName);
            } catch (Exception e) {
                String excErr = e.getMessage();
                LOGGER.info("New Tag will be created : {}", tagName, excErr);
            }
            if (existingTag.isPresent()) {
                tagDto.setId(existingTag.get().getId());
            } else {
                tagDto.setId(tagDtoDao.saveTag(tagDto));
            }
        }
    }

}
