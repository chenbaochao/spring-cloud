package com.play001.cloud.support.entity;

import java.util.List;

/**
 * 分页类
 */
public class Pagination<T> {
    private Long totalData;//数据总数
    private Integer totalPage;//页面总数
    private Integer pageNo;//当前页面编号
    private List<T> data;//附带数据

    public Long getTotalData() {
        return totalData;
    }

    public void setTotalData(Long totalData) {
        this.totalData = totalData;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
