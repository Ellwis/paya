package com.paya.pleaserservice.core;


import lombok.Getter;

import java.util.HashMap;

@Getter
public class GeneralSearchModel {
    private HashMap<String, Object> filters;
    private HashMap<String, String> sorting;
    private Integer pageSize;
    private Integer pageNumber;

    public void setFilters(HashMap<String, Object> filters) {
        this.filters = filters;
    }

    public void setSorting(HashMap<String, String> sorting) {
        this.sorting = sorting;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && 0 < pageSize && pageSize <= 100) {
            this.pageSize = pageSize;
        } else
            this.pageSize = 5;
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber == 0) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;

        }
    }
}
