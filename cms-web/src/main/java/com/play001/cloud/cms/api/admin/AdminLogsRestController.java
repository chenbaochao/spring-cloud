package com.play001.cloud.cms.api.admin;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/administrator/loginLog")
public class AdminLogsRestController {

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 获取登陆日志
     * @param adminId 管理员ID
     * @param offset 开始位置
     * @param limit 数据条数
     */
    @PermissionCode
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> getLoginLogs(Integer adminId, Long offset, Integer limit){
        return loginLogService.getPagination(adminId, offset, limit);
    }
}
