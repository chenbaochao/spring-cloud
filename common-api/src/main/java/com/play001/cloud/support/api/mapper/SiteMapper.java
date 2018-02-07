package com.play001.cloud.support.api.mapper;

import com.play001.cloud.support.entity.SiteConf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SiteMapper {

    @Select("select id, title, description, url from os_site_conf where id = 1")
    SiteConf getConf();
}
