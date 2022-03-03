package com.jude.StudentManagementSystem.model.page;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    private Long count;
    private List<T> content;

    public Page(Long count, List<T> content) {
        this.count = count;
        this.content = content;
    }

    public Page() {
        this.count = 0L;
        this.content = new ArrayList<>();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
