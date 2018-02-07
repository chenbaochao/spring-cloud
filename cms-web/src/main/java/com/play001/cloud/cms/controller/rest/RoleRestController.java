package com.play001.cloud.cms.controller.rest;

import com.play001.cloud.cms.entity.Role;
import com.play001.cloud.cms.service.RoleService;
import com.play001.cloud.common.entity.Response;
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
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response<Integer> create(@RequestBody @Valid Role role, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new Response<Integer>().setErrMsg("参数错误");
        }
        return roleService.create(role);
    }

    //更新
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response<Integer> update(@RequestBody @Valid Role role, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new Response<Integer>().setErrMsg("参数错误");
        }
        return roleService.update(role);
    }
    //列表
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Role> getList(){
        return roleService.findAll();
    }

    //删除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response<Integer> delete(Integer id){
        return roleService.delete(id);
    }
}