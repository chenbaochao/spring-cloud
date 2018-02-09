package com.play001.cloud.support.mapper;

import com.play001.cloud.support.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {

    @Select("select id, url, path, storage_name as storageName, count from support_image_log where id = #{id} limit 1")
    Image findById(Long id);

    //图片引用数+1
    @Update("update support_image_log set count = count + 1 where id = #{id}")
    void increaseCount(Long id);
    //图片引用数-1
    @Update("update support_image_log set count = count - 1 where id = #{id}")
    void decreaseCount(Long id);
}
