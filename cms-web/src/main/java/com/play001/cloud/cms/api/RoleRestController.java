package com.play001.cloud.cms.api;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.entity.Role;
import com.play001.cloud.cms.service.RoleService;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleRestController {

    private RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 创建
     */
    @PermissionCode("role_create")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@RequestBody @Valid Role role, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数错误");
        }
        return roleService.create(role);
    }

    //更新
    @PermissionCode("role_update")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Integer> update(@RequestBody @Valid Role role, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数错误");
        }
        return roleService.update(role);
    }
    //列表
    @PermissionCode("role_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Role> getList(){
        return roleService.findAll();
    }

    //删除
    @PermissionCode("role_delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> delete(Integer id){
        return roleService.delete(id);
    }
}
