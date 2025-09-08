package org.example.springdatajpapractice.DTOs;

import java.util.List;

public class PagedResponse<T> {
    private List<T> items;
    private int page;
    private int pageSize;
    private String nextPageUrl;
    private String prevPageUrl;

    public PagedResponse(List<T> items, int page, int pageSize, String nextPageUrl, String prevPageUrl) {
        this.items = items;
        this.page = page;
        this.pageSize = pageSize;
        this.nextPageUrl = nextPageUrl;
        this.prevPageUrl = prevPageUrl;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }
}
