package com.play001.cloud.cms.mapper;

import com.play001.cloud.common.entity.Image;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {

    /**
     * 添加
     */
    @Insert("insert into support_image_log(url, path, create_time, used, storage_name) value(#{url}, #{path}, #{createTime}, #{used}, #{storageName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Image image);

    /**
     * 通过ID查找
     */
    @Select("select id, url, path, used, storage_name as storageName, create_time as createTime from support_image_log where id = #{id}")
    Image findById(Long id);

    /**
     * 设置图片为已用
     */
    @Update("update support_image_log set used = 1 where id = #{id}")
    void setUsed(Long id);

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
