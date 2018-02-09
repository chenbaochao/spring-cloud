package com.play001.cloud.support.entity.user;


import com.play001.cloud.support.entity.Image;
import com.play001.cloud.support.entity.product.Product;
import com.play001.cloud.support.entity.product.Specification;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 购物车
 */
public class ShopCart  implements Serializable {

    public static final byte INVALID = 0;
    public static final byte VALID = 1;
    private Long id;
    @NotNull(message = "产品信息不能为空")
    private Product product;
    @NotNull(message = "产品规格不能为空")
    private Specification spec;//产品规格
    @Min(value = 1, message = "购买数量必须大于0")
    private Integer buyNumber;//购买数量
    private User user;
    //0表示失效,1表示正常
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
