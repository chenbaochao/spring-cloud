package com.play001.cloud.support.entity.user;


import com.play001.cloud.support.entity.Product;
import com.play001.cloud.support.entity.Product.Specification;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 购物车
 */
public class ShopCart  implements Serializable {

    //正常状态
    public static final byte STATUS_NORMAL = 1;
    //已卖完
    public static final byte STATUS_NO_STOCK = 2;
    //产品或规格已下架
    public static final byte STATUS_INVALID = 3;
    private Long id;
    @NotNull(message = "产品信息不能为空")
    private Product product;
    @NotNull(message = "产品规格不能为空")
    private Specification spec;//产品规格
    @Min(value = 1, message = "购买数量必须大于0")
    private Integer buyNumber;//购买数量
    private User user;

    private Byte status;

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


    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
