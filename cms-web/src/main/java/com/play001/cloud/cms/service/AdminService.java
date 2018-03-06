package com.play001.cloud.cms.service;

import com.play001.cloud.cms.entity.AdminSessionData;
import com.play001.cloud.cms.entity.LoginLog;
import com.play001.cloud.cms.mapper.AdminMapper;
import com.play001.cloud.cms.entity.Admin;
import com.play001.cloud.cms.mapper.ImageMapper;
import com.play001.cloud.cms.mapper.LoginLogMapper;
import com.play001.cloud.cms.mapper.PermissionMapper;
import com.play001.cloud.cms.util.CommonUtil;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.Image;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.enums.StorageTypeEnum;
import com.play001.cloud.support.util.DateUtil;
import com.play001.cloud.cms.util.storage.IBaseStorageUtil;
import com.play001.cloud.cms.util.storage.StorageFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class AdminService {

    public static final int ADMIN_ROOT_ID = 1;

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private StorageFactory storageFactory;

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Integer> create(Admin admin ) throws IException {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(admin.getRole().getId().equals(RoleService.ROOT_ROLE_ID)){
            return responseEntity.setErrMsg("不能创建root用户");
        }
        //判断username是否重复
        if(adminMapper.findByUsername(admin.getUsername()) != null ){
            return responseEntity.setErrMsg("用户名重复");
        }
        //md5加密
        admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        //设置创建时间
        admin.setCreateTime(DateUtil.getTime());
        adminMapper.add(admin);
        //根据用户组设置权限
        permissionMapper.add(admin.getId(), admin.getRole().getId());
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }

    //设置用户状态,启用或冻结=1/0
    public void setStatus(Integer id, byte status){
        adminMapper.setStatus(id, status);
    }
    /**
     * 登陆
     */
    public ResponseEntity<Integer> login(String username, String password, String captchaCode, HttpServletRequest request) throws IException {
        HttpSession session = request.getSession();
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(username == null || username.length() < 1 ||
                password == null || password.length() < 1 ||
                captchaCode == null || captchaCode.length() < 1){
            return responseEntity.setErrMsg("参数错误!");
        }
        /* 转换为小写,session里面存储的已经是小写的验证码了 */
        captchaCode = captchaCode.toLowerCase();
        String trueCode = (String) session.getAttribute("loginCaptchaCode");

        //if(trueCode == null || !trueCode.equals(captchaCode)) throw new IException("验证码错误");
        //验证码通过后必须将让验证码失效,否者会有漏洞
        session.setAttribute("loginCaptchaCode", null);
        //md5加密
        password = DigestUtils.md5Hex(password);
        Admin admin = adminMapper.findByUsername(username);
        if(admin == null){
            return responseEntity.setErrMsg("账户不存在!");
        }
        if(admin.getStatus().equals((byte)0)){
            return responseEntity.setErrMsg("账户被冻结!");
        }
        if(!admin.getPassword().equals(password)) {
            return responseEntity.setErrMsg("用户名或密码错误!");
        }
        //将登陆信息写进login_log
        LoginLog loginLog = new LoginLog();
        loginLog.setAdminId(admin.getId());
        String ip;
        if ((ip = request.getHeader("x-forwarded-for")) == null) {
            ip= request.getRemoteAddr();
        }
        loginLog.setIp(ip);
        loginLog.setTime(DateUtil.getTime());
        loginLog.setLocation(CommonUtil.getLocationByIp(ip));
        loginLogMapper.add(loginLog);
        //登陆成功后,将用户的权限信息读出来放在session中
        AdminSessionData adminSessionData = new AdminSessionData();
        adminSessionData.setId(admin.getId());
        adminSessionData.setUsername(admin.getUsername());
        adminSessionData.setRole(admin.getRole());
        adminSessionData.setRealName(admin.getRealName());
        //读取权限
/*        List<Map<String, Object>> permission = permissionMapper.getMenuPermission(admin.getId());
        Map<String, Boolean> adminPermission = new HashMap<>();
        for(Map<String, Object> item : permission){
            adminPermission.put(item.get("menuCode").toString(), (Boolean)item.get("flag"));
        }
        adminSessionData.setPermission((HashMap<String, Boolean>) adminPermission);*/
        session.setAttribute("admin", adminSessionData);

        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }

    public Admin findById(Integer id){
        return adminMapper.findById(id);
    }

    @Transactional
    public Admin findTest() {
        adminMapper.findTest();
        return adminMapper.findTest();
    }

    /**
     * 更新管理员.需要开启事务权限
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Integer> update(Admin admin, HttpSession session) throws IException {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(admin.getId() == null){
            return responseEntity.setErrMsg("参数错误");
        }
        AdminSessionData adminSessionData = (AdminSessionData) session.getAttribute("admin");
        if(1 == admin.getId() && adminSessionData.getId() != 1){
            return responseEntity.setErrMsg("ROOT用户无法被别人修改");
        }
        //判断是否需要更新权限(根据roleId是否发生变化),先删除该用户所有权限,然后再加入新的权限
        Admin oldAdmin = adminMapper.findById(admin.getId());
        if(oldAdmin == null){
            return responseEntity.setErrMsg("用户不存在");
        }
        //不一致,需要更新权限
        if(!oldAdmin.getRole().getId().equals(admin.getRole().getId())){
            //先删除该用户的权限
            permissionMapper.deletePermission(admin.getId());
            //再根据该用户的用户组,加入权限
            permissionMapper.add(admin.getId(), admin.getRole().getId());
        }
        //更新基本信息
        adminMapper.update(admin);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
    /**
     * 分页
     */
    public Map<String, Object> Pagination(Integer offset, Integer limit){
        Map<String, Object> response = new HashMap<>();
        response.put("total", adminMapper.count());
        response.put("rows", adminMapper.Pagination(offset, limit));
        return response;
    }


    /**
     * 删除
     * @param id 管理员ID
     */
    public boolean delete(Integer id) throws IException {
        if(id  == ADMIN_ROOT_ID) throw new IException("无法删除ROOT用户");
        //2.获取事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //3.设置事务隔离级别，开启新事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //4.获得事务状态
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //删除基本信息
            adminMapper.delete(id);
            //删除权限信息
            permissionMapper.deletePermission(id);
            //删除登陆日志记录
            loginLogMapper.deleteByAdminId(id);
            //提交
            transactionManager.commit(status);
            return true
                    ;
        }catch (Exception e){
            e.printStackTrace();
            //回滚
            transactionManager.rollback(status);
            return false;
        }

    }

    /**
     * 修改个人信息
     * 修改的内容包括姓名,性别,年龄,电话,电子邮箱
     */

    public ResponseEntity<Integer> updatePersonalInfo(Admin admin, HttpSession session){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        AdminSessionData adminSessionData = (AdminSessionData)session.getAttribute("admin");
        admin.setId(adminSessionData.getId());
        //自己修改自己的信息,status必然为1
        admin.setStatus((byte)1);
        admin.setRole(adminSessionData.getRole());
        adminMapper.update(admin);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }

    /**
     * 修改个人密码
     */
    public ResponseEntity<Integer> updatePersonalPwd(String oldPassword, String newPassword, HttpSession session){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        AdminSessionData adminSessionData = (AdminSessionData)session.getAttribute("admin");
        Admin admin = adminMapper.findById(adminSessionData.getId());
        newPassword = DigestUtils.md5Hex(newPassword);
        oldPassword = DigestUtils.md5Hex(oldPassword);

        if(!Objects.equals(admin.getPassword(), oldPassword)){
            return responseEntity.setErrMsg("原密码错误");
        }
        //更新密码
        adminMapper.updatePassword(adminSessionData.getId(), newPassword);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }

    /**
     * 更新头像
     * @param imageId 图片ID
     */
    @Transactional
    public ResponseEntity<Integer> updateAvatar(Long imageId, Integer adminId){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(imageId == null) {
            return responseEntity.setErrMsg("参数错误");
        }
        Admin admin = adminMapper.findById(adminId);

        Image newImage = imageMapper.findById(imageId);
        if(newImage == null){
            return responseEntity.setErrMsg("图片不存在");
        }
        adminMapper.updateAvatar(newImage.getUrl(), adminId);
        //设置图片为已使用
        imageMapper.increaseCount(imageId);
        //删除旧头像,删除失败不处理
        Image oldImage = imageMapper.findByUrl(admin.getAvatar());
        try {
            if(oldImage != null){
                imageMapper.delete(oldImage.getId());
                IBaseStorageUtil storageUtil = storageFactory.getStorageUtil(StorageTypeEnum.valueOf(oldImage.getStorageName()));
                if(storageUtil != null){
                    storageUtil.delete(oldImage.getPath());
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
}
