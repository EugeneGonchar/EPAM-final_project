package dao.util;

import pojo.dto.PageDTO;

public class QueryBuilder {

    public static String setQueryLimit(String query, PageDTO pageDTO){
        return query + " LIMIT " + getStartIndex(pageDTO) + ", " + getOffset(pageDTO);
    }

    private static int getStartIndex(PageDTO pageDTO){
        return (pageDTO.getCurrentPage() - 1) * pageDTO.getElementsOnPage();
    }

    private static int getOffset(PageDTO pageDTO){
        return pageDTO.getElementsOnPage();
    }
}
