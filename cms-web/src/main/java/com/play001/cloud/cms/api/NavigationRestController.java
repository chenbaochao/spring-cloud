package com.play001.cloud.cms.api;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.NavigationBarService;
import com.play001.cloud.cms.service.NavigationService;
import com.play001.cloud.support.entity.Navigation;
import com.play001.cloud.support.entity.NavigationBar;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/navigation")
public class NavigationRestController {

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private NavigationBarService navigationBarService;

    /**
     * 获取所有导航
     */
    @PermissionCode("navigation_view")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Map<String, Object> getList(){
        return navigationService.getList();
    }

    /**
     * 导航栏分页
     */
    @PermissionCode("navigation_view")
    @RequestMapping(value = "/bar/getList", method = RequestMethod.GET)
    public Map<String, Object> getBarList(Integer navigationId, Integer offset, Integer limit){
        return navigationBarService.getList(navigationId, offset, limit);
    }

    /**
     * 隐藏导航栏
     */
    @PermissionCode("navigation_update")
    @RequestMapping(value = "/bar/hidden", method = RequestMethod.POST)
    public ResponseEntity<Integer> hiddenBar(Integer id){
        return navigationBarService.setStatus(id, false);
    }
    /**
     * 显示导航栏
     */
    @PermissionCode("navigation_update")
    @RequestMapping(value = "/bar/show", method = RequestMethod.POST)
    public ResponseEntity<Integer> showBar(Integer id){
        return navigationBarService.setStatus(id, true);
    }
    /**
     * 更新导航栏
     */
    @PermissionCode("navigation_update")
    @RequestMapping(value = "/bar/update", method = RequestMethod.POST)
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
    @RequestMapping(value = "/bar/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> deleteBar(Integer id){
        return navigationBarService.delete(id);
    }
    /**
     * 创建导航栏
     */
    @PermissionCode("navigation_create")
    @RequestMapping(value = "/bar/create", method = RequestMethod.POST)
    public ResponseEntity<Integer> createBar(@Validated NavigationBar navigationBar, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return navigationBarService.create(navigationBar);
    }
}
