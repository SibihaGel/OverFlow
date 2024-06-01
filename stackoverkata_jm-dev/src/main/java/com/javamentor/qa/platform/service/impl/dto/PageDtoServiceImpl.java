package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.service.abstracts.dto.*;
import org.springframework.data.domain.*;
import java.util.*;

public abstract class PageDtoServiceImpl<T, P> implements DtoService<T, P> {

    public abstract List<T> fetchData(P param, int offset, int limit);

    @Override
    public PageRequest getItems(P param, int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize; // определяет размер смещения страницы
        List<T> items = fetchData(param, offset, pageSize); // устанавливает количество выводимых элементов
        int totalResultCount = getTotalResultCount(param); // выводит количество строк
        int totalPageCount = (int) Math.ceil((double) totalResultCount / pageSize); // ... количество

        return PageRequest.of(pageNumber, pageSize);
    }
}