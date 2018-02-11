package com.play001.cloud.support.entity.product;

import java.io.Serializable;

/**
 * 产品规格,数量,价格属性
 */
public class Specification  implements Serializable {

    private Long id;
    private Long productId;
    private String name;
    private Integer stock;
    private Integer soldNumber;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

}
