package com.play001.cloud.web.entity;

/**
 * 分页类
 */
public class Pagination {
    private Integer dataQuantity;//数据总数
    private Integer pageQuantity;//页面总数
    private Integer pageNo;//当前页面编号
    private Integer pageSize;//一页显示多少条数据

    public Integer getDataQuantity() {
        return dataQuantity;
    }

    public void setDataQuantity(Integer dataQuantity) {
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
}
