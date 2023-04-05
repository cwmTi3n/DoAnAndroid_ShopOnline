package com.app.shopdodientu.model;

import java.io.Serializable;
import java.util.List;

public class PageModel<T> implements Serializable {
    private int index;
    private int total;
    private List<T> content;

    public PageModel() {
    }

    public PageModel(int index, int total, List<T> content) {
        this.index = index;
        this.total = total;
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public int getTotal() {
        return total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
