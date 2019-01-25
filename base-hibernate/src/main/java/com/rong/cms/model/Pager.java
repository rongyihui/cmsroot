package com.rong.cms.model;

import java.util.List;

public class Pager<T> {
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
    private Long totalRecord;
    /**
     * 总共多少页
     */
    private int totalPage;
    /**
     * 放置具体数据的列表
     */
    private List<T> datas;

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
    public Long getTotalRecord() {
        return totalRecord;
    }
    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public List<T> getDatas() {
        return datas;
    }
    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
    public int getPageOffset() {
        return pageOffset;
    }
    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }
}
