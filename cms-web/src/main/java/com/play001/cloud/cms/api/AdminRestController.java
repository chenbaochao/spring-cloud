package com.play001.cloud.cms.api;


import com.play001.cloud.cms.entity.Admin;
import com.play001.cloud.cms.entity.AdminSessionData;
import com.play001.cloud.cms.service.AdminService;
import com.play001.cloud.cms.service.LoginLogService;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.RedisMessage;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.enums.RedisMessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 创建管理员
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@Validated Admin admin, BindingResult result, HttpSession session) throws IException {
        if(result.hasErrors()) throw new IException(result.getFieldError().getDefaultMessage());
        if(1 == admin.getRole().getId()) throw new IException("不能创建ROOT用户!");
        Admin creator = new Admin();
        //获取创建者信息
        AdminSessionData adminSessionData = (AdminSessionData) session.getAttribute("admin");
        creator.setId(adminSessionData.getId());
        admin.setCreator(creator);
        adminService.create(admin);
        return new ResponseEntity<>(ResponseEntity.SUCCESS);
    }

    /**
     * 登陆
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Integer> login(String username, String password, String captchaCode, HttpServletRequest request) throws IException {
        return  adminService.login(username, password, captchaCode, request);
    }

    /**
     * 获取管理员列表
     * @param offset 开始位置
     * @param limit 条数
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Map<String, Object> getList(Integer offset, Integer limit){
        return adminService.Pagination(offset, limit);
    }

    /**
     * @param id 要删除的管理员ID, 不能为1,
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(Integer id) throws IException {
        if(id == null) throw new IException("ID不能为空");
        adminService.delete(id);
        return new ResponseEntity<>(ResponseEntity.SUCCESS);
    }

    /**
     * 更新管理员
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(Admin admin, HttpSession session) throws IException {
        return adminService.update(admin, session);
    }

    /**
     * 冻结管理员
     */
    @RequestMapping(value = "/freeze", method = RequestMethod.PUT)
    public ResponseEntity<Integer> freeze(Integer id) throws IException {
        if(id == null) throw new IException("参数错误");
        adminService.setStatus(id, (byte)0);
        return new ResponseEntity<>(ResponseEntity.SUCCESS);
    }
    /**
     * 启用管理员
     */
    @RequestMapping(value = "/unFreeze", method = RequestMethod.PUT)
    public ResponseEntity<Integer> unFreeze(Integer id) throws IException {
        if(id == null) throw new IException("参数错误");
        adminService.setStatus(id, (byte)1);
        return new ResponseEntity<>(ResponseEntity.SUCCESS);
    }

    /**
     * 获取登陆日志
     * @param adminId 管理员ID
     * @param offset 开始位置
     * @param limit 数据条数
     */
    @RequestMapping(value = "/getLoginLogs", method = RequestMethod.GET)
    public Map<String, Object> getLoginLogs(Integer adminId, Long offset, Integer limit){
        return loginLogService.getPagination(adminId, offset, limit);
    }
    /**
     * 修改个人信息
     */
    @RequestMapping(value = "/updatePersonalInfo", method = RequestMethod.POST)
    public ResponseEntity<Integer> updatePersonalInfo(@Validated Admin admin, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(result.getFieldError().getDefaultMessage());
        }
        return adminService.updatePersonalInfo(admin, session);
    }
    /**
     * 修改密码
     */
    @RequestMapping(value = "/updatePersonalPwd", method = RequestMethod.POST)
    public ResponseEntity<Integer> updatePersonalPwd(String oldPassword, String newPassword, HttpSession session){
        return adminService.updatePersonalPwd(oldPassword, newPassword, session);
    }

    /**
     * 修改头像
     */
    @RequestMapping(value = "/updateAvatar", method = RequestMethod.POST)
    public ResponseEntity<Integer> updateAvatar(Long imageId, HttpSession session){
        AdminSessionData adminSessionData = (AdminSessionData)session.getAttribute("admin");
        return adminService.updateAvatar(imageId, adminSessionData.getId());
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        RedisMessage redisMessage = new RedisMessage(RedisMessageEnum.ADVERT_CHANGE);
        redisTemplate.convertAndSend(RedisMessage.CHANNEL, redisMessage);
        return  "11";
    }
}
