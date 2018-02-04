package com.play001.cloud.cms.mapper;

import com.play001.cloud.cms.cache.MybatisRedisCache;
import com.play001.cloud.cms.entity.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import java.util.List;
import java.util.Map;


@Mapper
@Repository
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface AdminMapper {

    /**
     * 更新头像
     */
    @Select("update cms_admin set avatar = #{url} where id = #{adminId}")
    void updateAvatar(@Param("url") String url, @Param("adminId") Integer adminId);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "real_name", property = "realName"),
            @Result(property="role",column="role_id",one=@One(select="com.play001.cloud.cms.mapper.RoleMapper.findById"))
    })
    @Select("select id, username, password, real_name, role_id, status  from cms_admin where username = #{username} limit 1")
    Admin findByUsername(String username);

    /**
     * 资料分页
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(property="role",column="role_id",one=@One(select="com.play001.cloud.cms.mapper.RoleMapper.findById"))
    })
    @Select("select id, username, real_name, age, email, telephone, status, create_time, role_id  from cms_admin limit #{offset}, #{limit}")
    List<Admin> Pagination(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select id, username, password from cms_admin where id = 1 limit 1")
    Admin findTest();
    /**
     * 更新
     */
    @Update("update cms_admin set real_name = #{realName}, email = #{email}, telephone = #{telephone}, sex = #{sex}, status = #{status}, role_id = #{role.id} " +
            " where id = #{id}")
    void update(Admin admin);



    /**
     * 插入
     */
    @Insert("insert into cms_admin(username, role_id, password, real_name, email, status, telephone, sex, creator_id) value(" +
            " #{username},#{role.id}, #{password}, #{realName}, #{email}, #{status}, #{telephone}, #{sex}, #{creator.id})")
    @Options(useGeneratedKeys = true)
    Integer add(Admin admin);

    /**
     * 删除用户基本信息
     */

    @Delete("delete from cms_admin where id = #{id}")
    void delete(Integer id);

    /**
     * 删除了role后,将role下的用户设置role为未分组
     */
    @Update("update cms_admin set role_id = #{defaultRoleId} where role_id = #{roleId}")
    void setRoleDefault(@Param("roleId") Integer roleId, @Param("defaultRoleId") int defaultRoleId);

    /**
     * findById
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(property="role",column="role_id",one=@One(select="com.play001.cloud.cms.mapper.RoleMapper.findById"))
    })
    @Select("select id, username, password, avatar, real_name, email, telephone, age, sex, status, role_id, create_time  from cms_admin where id = #{id} limit 1")
    Admin findById(Integer id);

    /**
     * 管理员列表 分页
     */
    @Select("select count(id) from cms_admin")
    Integer count();

    /**
     * 设置用户状态
     * @param status 冻结/启用= 1/0
     */
    @Update("update cms_admin set status = #{status} where id = #{id}")
    void setStatus(@Param("id") Integer id, @Param("status") byte status);

    /**
     * 修改密码
     */
    @Update("update cms_admin set password = #{password} where id = #{id}")
    void updatePassword(@Param("id") Integer id, @Param("password") String password);

}
