package com.play001.cloud.cms.mapper;

import com.play001.cloud.support.entity.Image;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {

    /**
     * 添加
     */
    @Insert("insert into support_image_log(url, path, create_time, count, storage_name) value(#{url}, #{path}, #{createTime}, #{count}, #{storageName})")
    @Options(useGeneratedKeys = true)
    void add(Image image);

    /**
     * 通过ID查找
     */
    @Select("select id, url, path, used, storage_name as storageName, create_time as createTime from support_image_log where id = #{id}")
    Image findById(Long id);

    //图片引用数+1
    @Update("update support_image_log set count = count + 1 where id = #{id}")
    void increaseCount(Long id);

    //图片引用数-1
    @Update("update support_image_log set count = count - 1 where id = #{id}")
    void decreaseCount(Long id);


    /**
     * 通过URL查找图片
     */
    @Select("select id, url, path, used, storage_name as storageName, create_time as createTime from support_image_log where url = #{url}")
    Image findByUrl(String url);


    /**
     * 删除图片数据
     */
    @Delete("delete from support_image_log where id = #{id}")
    Integer delete(Long id);

}
