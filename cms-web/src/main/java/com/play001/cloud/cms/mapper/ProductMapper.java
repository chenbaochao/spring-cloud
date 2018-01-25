package com.play001.cloud.cms.mapper;

import com.play001.cloud.common.entity.*;
import org.apache.ibatis.annotations.*;
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
    void addLabel(Label label);

    //findById
    @Select("select id, name, show_price, title, status, create_time, remarks, sold_number, category_id, thumb_id from os_product where id = #{productId} limit 1")
    @ResultMap("com.play001.cloud.cms.mapper.ProductMapper.productResult")
    Product findById(Long productId);


    /**
     * 查找产品相册
     */
    @Results({
            @Result(property = "image", column = "image_id", one = @One(select = "com.play001.cloud.cms.mapper.ImageMapper.findById"))
    })
    @Select("select id, sort, image_id from os_product_image where product_id = #{0} order by sort")
    List<ProductImage> findPicsByProductId(Long productId);

    /**
     * 查找商品参数
     */
    @Select("select id, name, value, sort from os_product_parameter where product_id = #{0} order by sort")
    List<Parameter> findParasByProductId(Long productId);

    /**
     * 查找商品规格
     */
    @Select("select id, name, stock, price, sold_number as soldNumber from os_product_specification where product_id = #{0}")
    List<Specification> findSpecsByProductId(Long productId);
    /**
     * 查找商品标签
     */
    @Select("select id, name, sort from os_product_label where product_id = #{0} order by sort")
    List<Parameter> findLabelsByProductId(Long productId);

    /**
     *查找商品介绍
     */
    @Select("select introduction from os_product_introduction where product_id = #{0} limit 1")
    String findIntroduction(Long productId);
}
