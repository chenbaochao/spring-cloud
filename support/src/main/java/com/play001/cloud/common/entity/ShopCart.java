package com.play001.cloud.common.entity;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 购物车
 */
public class ShopCart {
    private Long id;
    @NotNull(message = "产品信息不能为空")
    private Product product;
    @NotNull(message = "产品规格不能为空")
    private Specification spec;//产品规格
    @Min(value = 0, message = "购买数量必须大于0")
    private Integer buyQuantity;//购买数量
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Specification getSpec() {
        return spec;
    }

    public void setSpec(Specification spec) {
        this.spec = spec;
    }

    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Integer buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
}
