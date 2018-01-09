package com.play001.cloud.cms.service;

import com.play001.cloud.cms.entity.Role;
import com.play001.cloud.cms.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findAll(){
        return roleMapper.finAll();
    }
}
