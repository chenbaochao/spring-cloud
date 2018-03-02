package com.play001.cloud.product.api.mapper;

import com.play001.cloud.support.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    @Insert("insert into os_comment(product_id, user_id, username, user_avatar, order_id, star, content, status, create_time)" +
            " value(#{productId}, #{user.id}, #{user.username}, #{user.profilePhoto}, #{orderId}, #{star}, #{content}, #{status}, #{createTime})")
    void add(Comment comment);

    @Results({
            @Result(column = "product_id", property = "productId"),
            @Result(column = "username", property = "user.username"),
            @Result(column = "user_avatar", property = "user.profilePhoto"),
            @Result(column = "create_time", property = "createTime")
    })
    @Select("select product_id,user_id, username, user_avatar, star, content, status, create_time from os_comment" +
            " where product_id = #{productId} order by id desc limit #{offset}, #{limit} ")
    List<Comment> getList(@Param("productId")Long productId, @Param("offset")Long offset, @Param("limit")Integer limit);

    @Select("select count(id) from os_comment where product_id = #{productId}")
    Long countByProductId(Long productId);
}
