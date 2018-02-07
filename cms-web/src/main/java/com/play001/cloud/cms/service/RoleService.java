package com.play001.cloud.cms.service;

import com.play001.cloud.cms.entity.MenuPermission;
import com.play001.cloud.cms.entity.Role;
import com.play001.cloud.cms.mapper.AdminMapper;
import com.play001.cloud.cms.mapper.RoleMapper;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Objects;

@Service
public class RoleService {

    public static final int ROOT_ROLE_ID = 1;//Root权限,不能被删除修改
    public static final int DEFAULT_ROLE_ID = 2;//未分组角色,不能被删除修改

    private RoleMapper roleMapper;
    private DataSourceTransactionManager transactionManager;
    private AdminMapper adminMapper;

    @Autowired
    public RoleService(RoleMapper roleMapper, DataSourceTransactionManager transactionManager,
                       AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
        this.roleMapper = roleMapper;
        this.transactionManager = transactionManager;
    }

    public List<Role> findAll(){
        return roleMapper.finAll();
    }

    //findById
    public Role findById(Integer id){
        return roleMapper.findById(id);
    }
    //新增
    public ResponseEntity<Integer> create(Role role){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        //开启事务
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            //先add role,再add role_menu
            roleMapper.add(role);
            //循环add 权限信息
            for(MenuPermission permission : role.getPermissions()){
                //新增的权限默认是无权限,只有用户勾选了,这里才会出现数据,所以flag直接为1
                roleMapper.addPermission(role.getId(),permission.getMenu().getId(), (byte)1);
            }
            transactionManager.commit(status);
            return responseEntity.setStatus(ResponseEntity.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(status);
            return responseEntity.setErrMsg("保存失败");
        }
    }
    //更新
    public ResponseEntity<Integer> update(Role newRole) {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(newRole.getId() == null){
            return responseEntity.setErrMsg("参数错误");
        }
        if(newRole.getId().equals(ROOT_ROLE_ID) || newRole.getId().equals(DEFAULT_ROLE_ID)){
            return responseEntity.setErrMsg("此分组不能被修改");
        }
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            //获取旧数据,以便后面更新权限信息时比较
            Role oldRole = roleMapper.findById(newRole.getId());
            if(oldRole == null) throw new IException("数据不存在");
            roleMapper.update(newRole);
            //
            List<MenuPermission> oldRolePermissions = oldRole.getPermissions();
            List<MenuPermission> newRolePermissions = newRole.getPermissions();
            //把旧权限数据统一设置为没有权限
            for(MenuPermission oldItem : oldRolePermissions){
                roleMapper.updatePermission(oldRole.getId(),oldItem.getMenu().getId(), (byte)0);
            }
            boolean flag;//新的权限是否已经存在数据库中了,是则更新,否则增加
            for(MenuPermission newItem : newRolePermissions){
                flag = false;
                for(MenuPermission oldItem : oldRolePermissions){
                    if(Objects.equals(newItem.getMenu().getId(), oldItem.getMenu().getId())){
                        flag = true;
                    }
                }
                if(flag){
                    roleMapper.updatePermission(newRole.getId(),newItem.getMenu().getId(), newItem.getFlag());
                }else{
                    roleMapper.addPermission(newRole.getId(), newItem.getMenu().getId(), newItem.getFlag());
                }
            }
            transactionManager.commit(status);
            return responseEntity.setStatus(ResponseEntity.SUCCESS);
        }catch (IException e){
            transactionManager.rollback(status);
            return responseEntity.setErrMsg(e.getMessage());
        }catch (Exception e){
            transactionManager.rollback(status);
            return responseEntity.setErrMsg("操作失败");
        }
    }
    public ResponseEntity<Integer> delete(Integer id){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");
        }
        if(id.equals(ROOT_ROLE_ID) || id.equals(DEFAULT_ROLE_ID)){
            return responseEntity.setErrMsg("无法删除此角色");
        }
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            roleMapper.delete(id);
            roleMapper.deletePermissions(id);
            //将此分组下的管理员角色置为未分组
            adminMapper.setRoleDefault(id, DEFAULT_ROLE_ID);
            return responseEntity.setStatus(ResponseEntity.SUCCESS);
        }catch (Exception e){
            transactionManager.rollback(status);
            return responseEntity.setErrMsg("操作失败");
        }
    }
}
