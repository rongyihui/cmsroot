package com.rong.cms.model;

import java.util.List;

public class Pager {
    /**
     * 第几页
     */
    private int pageIndex;
    /**
     * 每页显示多少条  默认20
     */
    private int pageSize;
    /**
     * 分页的开始值
     */
    private int pageOffset;
    /**
     * 总共多少条记录
     */
    private Long count;
    /**
     * 总共多少页
     */
    private int totalPage;
    /**
     * 放置具体数据的列表
     */
    private List data;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }
}
