package com.play001.cloud.commonapi.mapper;

import com.play001.cloud.common.entity.ShopCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 购物车
 */
@Mapper
public interface CartMapper {

    //加购
    @Insert("insert into os_shop_cart(product_id, product_spec_id, user_id, buy_quantity) " +
            "values(#{product.id}, #{spec.id}, #{user.id}, 1)")
    void add(ShopCart shopCart);

    //删除
    @Delete("delete from os_shop_cart where id = #{id}")
    void delete(Long id);

    //列出全部
    List<ShopCart> list(Long userId);
}
