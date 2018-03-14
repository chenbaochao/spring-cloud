package com.play001.cloud.cms.api.navigation;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.NavigationBarService;
import com.play001.cloud.cms.service.NavigationService;
import com.play001.cloud.support.entity.NavigationBar;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/navigation/bar")
public class NavigationBarRestController {

    @Autowired
    private NavigationBarService navigationBarService;
    /**
     * 导航栏分页
     */
    @PermissionCode("navigation_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> getBarList(Integer navigationId, Integer offset, Integer limit){
        return navigationBarService.getList(navigationId, offset, limit);
    }

    /**
     * 显示导航栏
     */
    @PermissionCode("navigation_update")
    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.POST)
    public ResponseEntity<Integer> showBar(@PathVariable Integer id, @PathVariable Byte status){
        return navigationBarService.setStatus(id, status);
    }
    /**
     * 更新导航栏
     */
    @PermissionCode("navigation_update")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Integer> updateBar(@Validated NavigationBar navigationBar, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return navigationBarService.update(navigationBar);
    }
    /**
     * 删除导航栏
     */
    @PermissionCode("navigation_delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteBar(@PathVariable Integer id){
        return navigationBarService.delete(id);
    }
    /**
     * 创建导航栏
     */
    @PermissionCode("navigation_create")
    @RequestMapping(value = "/bar", method = RequestMethod.PUT)
    public ResponseEntity<Integer> createBar(@Validated NavigationBar navigationBar, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return navigationBarService.create(navigationBar);
    }
}
