package com.jude.StudentManagementSystem.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagingResponse<T> extends BaseResponse {

    private long count;
    private List<T> data;
    private long pages;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public static long getPages(long count, long pageSize) {
        if (count < 1) {
            return 0;
        }
        if (pageSize < 1) {
            return 1;
        }
        return count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
    }
}
