package com.play001.cloud.cms.mapper.admin;

import com.play001.cloud.cms.entity.LoginLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginLogMapper {

    /**
     * 分页
     */
    @Select("select id, ip, time, location from cms_admin_login_log where admin_id = #{adminId} order by time desc limit #{offset}, #{limit}")
    List<LoginLog> pagination(@Param("adminId") Integer adminId,@Param("offset") Long offset, @Param("limit")Integer limit);

    /**
     * 插入
     */
    @Insert("insert into cms_admin_login_log(admin_id, ip, time, location) value(#{adminId}, #{ip}, #{time}, #{location})")
    void add(LoginLog loginLog);

    /**
     * 分页计数
     */
    @Select("select count(id) from cms_admin_login_log where admin_id = #{adminId}")
    Integer count(Integer adminId);


    /**
     * 删除指定adminId的登陆信息
     */
    @Delete("delete from cms_admin_login_log where admin_id = #{adminId}")
    void deleteByAdminId(Integer adminId);
}
