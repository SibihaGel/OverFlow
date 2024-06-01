package com.javamentor.qa.platform.service.abstracts.dto;

import org.springframework.data.domain.*;
import java.util.*;

public interface DtoService<T, P>  {
    List<T> getItems(P param);
    int getTotalResultCount(P param);
    PageRequest getItems(P param, int pageNumber, int pageSize);
}

