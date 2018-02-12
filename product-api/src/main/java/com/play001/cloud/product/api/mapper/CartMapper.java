package com.play001.cloud.product.api.mapper;

import com.play001.cloud.support.entity.user.ShopCart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车
 */
@Mapper
@Repository
public interface CartMapper {

    //加购
    @Insert("insert into os_shop_cart(product_id,  product_spec_id, product_price, user_id, buy_number, product_name, product_spec_name, product_thumb_id, status) " +
            "values(#{product.id}, #{spec.id}, #{spec.price}, #{user.id}, 1, #{product.name}, #{spec.name}, #{product.thumb.id}, 1)")
    void add(ShopCart shopCart);

    //listByUserId列出全部
    @Select("SELECT id, buy_number, product_id,  product_price, product_spec_id, product_name, product_spec_name, product_thumb_id, user_id, status" +
            " FROM os_shop_cart WHERE user_id = #{userId}")
    @ResultMap("cartResult")
    List<ShopCart> listByUserId(Long userId);

    //删除
    @Delete("delete from os_shop_cart where id = #{cartId} and user_id = #{userId}")
    void delete(@Param("cartId")Long cartId, @Param("userId") Long userId);

    //通过产品ID和规格ID和用户Id查找是购物车Id
    @Select("select id from os_shop_cart where product_id = #{productId} and product_spec_id = #{specId} and user_id = #{userId} limit 1")
    Long findShopCartId(@Param("productId") Long productId, @Param("specId") Long specId, @Param("userId") Long userId);

    //增加购买数量
    @Update("update os_shop_cart set buy_number = buy_number + 1 where id = #{shopCartId} and user_id = #{userId}")
    void increaseBuyNumberByCartId(@Param("shopCartId")Long shopCartId, @Param("userId") Long userId);

    @Select("SELECT id, buy_number, status, product_price, product_id, product_spec_id, product_name, product_spec_name, product_thumb_id, user_id " +
            " FROM os_shop_cart WHERE id = #{id} and user_id = #{userId} limit 1")
    @ResultMap("cartResult")
    ShopCart findById(@Param("id") Long id, @Param("userId") Long userId);

    @Update("update os_shop_cart set status = #{status}, product_price = #{spec.price}, product_name = #{product.name}, product_spec_name = #{spec.name}" +
            " where id = #{id}")
    void update(ShopCart shopCart);
}
