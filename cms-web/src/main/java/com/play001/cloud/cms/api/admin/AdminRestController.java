package com.play001.cloud.cms.api.admin;


import com.play001.cloud.cms.Interceptor.PermissionCode;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/administrator")
public class AdminRestController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 创建管理员
     */
    @PermissionCode("admin_create")
    @RequestMapping(method = RequestMethod.POST)
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
     * 获取管理员列表
     * @param offset 开始位置
     * @param limit 条数
     */
    @PermissionCode("admin_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> getList(Integer offset, Integer limit){
        return adminService.Pagination(offset, limit);
    }

    /**
     * @param id 要删除的管理员ID, 不能为1,
     */

    @PermissionCode("admin_delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> delete(@PathVariable Integer id) throws IException {
        if(id == null) throw new IException("ID不能为空");
        return  adminService.delete(id);
    }

    /**
     * 更新管理员
     */

    @PermissionCode("admin_update")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Integer> update(Admin admin, HttpSession session) throws IException {
        return adminService.update(admin, session);
    }

    /**
     * 冻结/启用管理员
     */
    @PermissionCode("admin_update")
    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PATCH)
    public ResponseEntity<Integer> freeze(@PathVariable Integer id, @PathVariable Byte status) throws IException {
        return adminService.setStatus(id, status);
    }


    /**
     * 修改个人信息
     */
    @PermissionCode
    @RequestMapping(value = "/self", method = RequestMethod.PUT)
    public ResponseEntity<Integer> updatePersonalInfo(@Validated Admin admin, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(result.getFieldError().getDefaultMessage());
        }
        return adminService.updatePersonalInfo(admin, session);
    }
    /**
     * 修改密码
     */
    @RequestMapping(value = "/self/password", method = RequestMethod.PUT)
    public ResponseEntity<Integer> updatePersonalPwd(String oldPassword, String newPassword, HttpSession session){
        return adminService.updatePersonalPwd(oldPassword, newPassword, session);
    }

    /**
     * 修改头像
     */

    @PermissionCode
    @RequestMapping(value = "/avatar", method = RequestMethod.PUT)
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
