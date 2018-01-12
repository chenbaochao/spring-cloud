package com.play001.cloud.cms.service;

import com.play001.cloud.cms.entity.LoginLog;
import com.play001.cloud.cms.mapper.LoginLogMapper;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    /**
     * 分页
     */
    public Map<String, Object> getPagination(Integer adminId, Long offset, Integer limit){
        List<LoginLog> loginLogs = loginLogMapper.pagination(adminId, offset, limit);
        Map<String, Object> response = new HashMap<>();
        response.put("total", loginLogMapper.count(adminId));
        response.put("rows", loginLogs);
        return response;
    }
}
