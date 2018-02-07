package com.play001.cloud.cms.mapper;

import com.play001.cloud.support.entity.NavigationBar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 导航栏
 */
@Mapper
@Repository
public interface NavigationBarMapper {

    @Insert("insert into os_navigation_bar(navigation_id, name, target, sort, href, status, create_time, remarks)" +
            " value(#{navigation.id}, #{name}, #{target}, #{sort}, #{href}, #{status}, #{createTime}, #{remarks})")
    void add( NavigationBar navigationBar);

    @Select("select id, name, target, sort, href, status, create_time as createTime, remarks " +
            "from os_navigation_bar where navigation_id = #{navigationId} " +
            " limit #{offset}, #{limit}")
    List<NavigationBar> pagination(@Param("navigationId")Integer navigationId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select count(id) from os_navigation_bar where navigation_id = #{navigationId}")
    Integer count(Integer navigationId);

    @Select("select id, name, target, sort, href, status, create_time as createTime, remarks " +
            "from os_navigation_bar where id = #{id}")
    NavigationBar findById(Integer id);

    /**
     * 查找附加导航数据
     */
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "navigation_id", property = "navigation", one = @One(select = "com.play001.cloud.cms.mapper.NavigationMapper.findById"))
    })
    @Select("select id, name, target, sort, href, status, remarks, create_time, navigation_id " +
            "from os_navigation_bar where id = #{id}")
    NavigationBar findWithNavigation(Integer id);

    /**
     * 设置状态
     */
    @Update("update os_navigation_bar set status = #{status} where id = #{id}")
    void setStatus(@Param("id") Integer id, @Param("status") Boolean status);


    @Delete("delete from os_navigation_bar where id = #{id}")
    int delete(Integer id);

    @Update("update os_navigation_bar set name = #{name}, target = #{target}, sort = #{sort}, href = #{href}, status = #{status}," +
            " remarks = #{remarks} where id = #{id}")
    void update(NavigationBar navigationBar);
}
