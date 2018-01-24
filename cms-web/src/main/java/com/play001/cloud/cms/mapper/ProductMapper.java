package com.play001.cloud.cms.mapper;

import com.play001.cloud.common.entity.Parameter;
import com.play001.cloud.common.entity.Product;
import com.play001.cloud.common.entity.ProductImage;
import com.play001.cloud.common.entity.Specification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    @Insert("insert into os_product(name, show_price, title, status, create_time, remarks, sold_number, category_id, thumb_id)" +
            " value(#{name}, #{showPrice}, #{title}, #{status}, #{createTime}, #{remarks}, #{soldNumber}, #{category.id}, #{thumb.id} )")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addProduct(Product product);

    Long count(@Param("categoryId") Integer categoryId);

    List<Product> getList(@Param("offset") Long offset, @Param("limit") Integer limit,
                          @Param("sort") String sort, @Param("order") String order,
                          @Param("categoryId") Integer categoryId);

    //添加产品规格
    @Insert("insert into os_product_specification(product_id, name, price, stock, sold_number)" +
            " value( #{productId}, #{name}, #{price}, #{stock}, #{soldNumber})")
    void addSpec(Specification specification);

    //添加产品参数
    @Insert("insert into os_product_parameter(product_id, name, value, sort)" +
            "value(#{productId}, #{name}, #{value}, #{sort} )")
    void addPara(Parameter parameter);

    //增加产品介绍
    @Insert("insert into os_product_introduction(introduction, product_id) " +
            "value(#{introduction}, #{productId})")
    void addIntroduction(@Param("productId") Long productId, @Param("introduction") String introduction);

    //添加商品相册
    @Insert("insert into os_product_image(product_id, sort, image_id)" +
            " value(#{productId}, #{sort}, #{image.id})")
    void addPic(ProductImage productImage);

    //添加label
    @Insert("insert into os_product_label(product_id, sort, name)" +
            " value(#{productId}, " +
            " (select max_sort from (select IFNULL(max(sort)+1,1) max_sort from os_product_label where product_id  = #{productId})t ), " +
            " #{name})")
    void addLabel(@Param("productId")Long productId, @Param("name")String name);


}
