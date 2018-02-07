package com.play001.cloud.support.api.mapper;

import com.play001.cloud.support.entity.ShopCart;
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
    @Insert("insert into os_shop_cart(product_id, product_spec_id, user_id, buy_quantity) " +
            "values(#{product.id}, #{spec.id}, #{user.id}, 1)")
    void add(ShopCart shopCart);

    //列出全部
    List<ShopCart> list(Long userId);

    //删除
    @Delete("delete from os_shop_cart where id = #{cartId} and user_id = #{userId}")
    void delete(@Param("cartId") Long cartId, @Param("userId") Long userId);
}
