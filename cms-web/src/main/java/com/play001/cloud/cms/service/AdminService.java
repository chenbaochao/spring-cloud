package com.play001.cloud.cms.service;

import com.play001.cloud.cms.entity.AdminSessionData;
import com.play001.cloud.cms.mapper.AdminMapper;
import com.play001.cloud.cms.entity.Admin;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.util.DateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;

    public void  create(Admin admin ) throws IException {
        //判断username是否重复
        adminMapper.findByUsername(admin.getUsername());
        if(adminMapper != null ) throw new IException("用户名重复");
        //md5加密
        admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        //设置创建时间
        admin.setCreateTime(DateUtil.getTime());
        adminMapper.add(admin);
    }

    /**
     * 登陆
     */
    public void login(String username, String password, String captchaCode, HttpSession session) throws IException {
        /* 转换为小写,session里面存储的已经是小写的验证码了 */
        captchaCode = captchaCode.toLowerCase();
        String trueCode = session.getAttribute("loginCaptchaCode").toString();
        //if(trueCode == null || !trueCode.equals(captchaCode)) throw new IException("验证码错误");
        //验证码通过后必须将让验证码失效,否者会有漏洞
        session.setAttribute("loginCaptchaCode", null);
        //md5加密
        password = DigestUtils.md5Hex(password);
        Admin admin = adminMapper.findByUsername(username);
        if(admin == null || !admin.getPassword().equals(password)) throw new IException("用户名或密码错误");
        //登陆成功后,将用户的权限信息读出来放在session中
        AdminSessionData adminSessionData = new AdminSessionData();
        adminSessionData.setId(admin.getId());
        adminSessionData.setUsername(admin.getUsername());
        adminSessionData.setRole(admin.getRole());
        adminSessionData.setRealName(admin.getRealName());

        List<Map<String, Object>> permission = adminMapper.getMenuPermission(admin.getId());
        Map<String, Boolean> adminPermission = new HashMap<>();
        for(Map<String, Object> item : permission){
            adminPermission.put(item.get("menuCode").toString(), (Boolean)item.get("flag"));
        }
        adminSessionData.setPermission((HashMap<String, Boolean>) adminPermission);
        session.setAttribute("admin", adminSessionData);
    }

    public Admin findById(Integer id){
        return adminMapper.findById(id);
    }

    /**
     * 分页
     */
    public List<Admin> Pagination(Integer offset, Integer limit){
        return adminMapper.Pagination(offset, limit);
    }

    //@Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws IException {
        if(id  == 1) throw new IException("无法删除ROOT用户");
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
            adminMapper.deletePermission(id);
            //提交
            transactionManager.commit(status);
        }catch (Exception e){
            e.printStackTrace();;
            //回滚
            transactionManager.rollback(status);
        }

    }

}
