package com.play001.cloud.support.entity;

import java.util.List;

/**
 * 分页类
 */
public class Pagination<T> {
    private Long dataQuantity;//数据总数
    private Integer pageQuantity;//页面总数
    private Integer pageNo;//当前页面编号
    private Integer pageSize;//一页显示多少条数据

    List<T> data;//附带数据

    public Long getDataQuantity() {
        return dataQuantity;
    }

    public void setDataQuantity(Long dataQuantity) {
        this.dataQuantity = dataQuantity;
    }

    public Integer getPageQuantity() {
        return pageQuantity;
    }

    public void setPageQuantity(Integer pageQuantity) {
        this.pageQuantity = pageQuantity;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
