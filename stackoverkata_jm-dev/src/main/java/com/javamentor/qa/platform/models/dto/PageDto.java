package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Список DTO")
public class PageDto<T> {

    @Schema(description = "Номер текущей страницы")
    private int currentPageNumber;

    @Schema(description = "Общее количество страниц")
    private int totalPageCount;

    @Schema(description = "Общее количество результатов")
    private int totalResultCount;

    @Schema(description = "Список элементов DTO")
    private List<T> items;

    @Schema(description = "Списков DTO на странице")
    private int itemsOnPage;
}