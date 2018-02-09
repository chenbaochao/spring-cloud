package com.play001.cloud.support.entity.product;

import com.play001.cloud.support.entity.Image;

import java.io.Serializable;

/**
 * 产品图片
 */
public class ProductImage  implements Serializable {
    private Long id;
    private Long productId;
    private Image image;
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
