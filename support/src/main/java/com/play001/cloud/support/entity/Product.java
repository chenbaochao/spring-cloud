package com.play001.cloud.support.entity;


import com.google.gson.Gson;
import com.play001.cloud.support.entity.user.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 产品
 */
public class Product  implements Serializable {

    private Long id;
    @NotBlank(message = "产品名字不能为空")
    private String name;//商品名字
    @Max(value = 999999, message = "产品价格不能大于999999")
    @Min(value = 0, message = "产品价格不能小于0")
    @NotNull(message = "产品价格错误")
    private Double showPrice;//展示价格
    @NotBlank(message = "商品简介不能为空")
    private String title;//商品简介
    @NotNull(message = "商品封面不能为空")
    private Image thumb;//封面
    private String introduction;//商品介绍
    @Max(value = 1, message = "商品状态错误")
    @Min(value = 0, message = "商品状态错误")
    @NotNull(message = "商品状态错误")
    private Integer status;//是否上架,0否,1是
    private String createTime;//创建时间
    private Integer soldNumber;//卖出数量
    @NotNull(message = "相册不能为空")
    private List<ProductImage> pics;//图片
    @NotNull(message = "分类错误")
    private Category category;//分类

    private List<Parameter> parameters;//参数详情
    private List<Label> labels;//商品标签
    @NotNull(message = "商品类别/套餐数量需大于0")
    private List<Specification> specs;//商品类别,套餐
    private String remarks;//备注

    /**
     * 产品标签
     */
    public static class Label  implements Serializable {
        private Long id;
        private Long productId;
        private String name;
        private Integer sort;

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

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }
    }

    //商品参数
    public static class Parameter  implements Serializable {
        private Long id;
        private Long productId;
        private String name;
        private String value;
        private Integer sort;//排序

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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }
    }

    /**
     * 产品图片
     */
    public static class ProductImage  implements Serializable {
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

    /**
     * 产品规格,数量,价格属性
     */
    public static class Specification  implements Serializable {

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(Double showPrice) {
        this.showPrice = showPrice;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Image getThumb() {
        return thumb;
    }

    public void setThumb(Image thumb) {
        this.thumb = thumb;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<Specification> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Specification> specs) {
        this.specs = specs;
    }

    public List<ProductImage> getPics() {
        return pics;
    }

    public void setPics(List<ProductImage> pics) {
        this.pics = pics;
    }

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }


}
