package com.play001.cloud.common.api.mapper;


import com.play001.cloud.common.entity.Navigation;
import com.play001.cloud.common.entity.NavigationBar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 导航, 导航栏
 */

@Mapper
@Repository
public interface NavigationMapper {


    @Select("select id, name, target, sort, href, status, create_time as createTime, remarks " +
            "from os_navigation_bar where navigation_id = #{navigationId} and status = 1 order by sort")
    List<NavigationBar> listNavigationBarByNavigationId(Integer navigationId);

    @Select("select id, name, status, create_time as createTime, remarks from os_navigation")
    List<Navigation> findAll();

    @Update("update os_navigation set status = #{status} where id = #{id}")
    void setStatus(@Param("id") Integer id, @Param("status") Boolean status);

    @Select("select id, name, status, create_time as createTime, remarks from os_navigation where id = #{id} limit 1")
    Navigation findById(Integer id);

    /**
     * 通过导航栏ID查找导航
     * @param navigationBarId 导航栏ID
     */
    @Select("select id, name, status, create_time as createTime, remarks from os_navigation where id = (" +
            " select navigation_id from os_navigation_bar where id = #{navigationBarId} limit 1) limit 1")
    Navigation findByNavigationBarId(Integer navigationBarId);

    /**
     * 只更新name, remarks, status
     */
    @Update("update os_navigation set name = #{name}, remarks = #{remarks}, status = #{status} where id = #{id}")
    void update(Navigation navigation);



}
