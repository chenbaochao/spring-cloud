package com.play001.cloud.cms.controller.rest;

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
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Map<String, Object> getList(){
        return navigationService.getList();
    }

    /**
     * 隐藏导航
     */
    @RequestMapping(value = "/hidden", method = RequestMethod.POST)
    public ResponseEntity<Integer> hidden(Integer id){
        return navigationService.setStatus(id, false);
    }
    /**
     * 显示导航
     */
    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public ResponseEntity<Integer> show(Integer id){
        return navigationService.setStatus(id, true);
    }
    /**
     * 更新导航
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@Validated  Navigation navigation, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return navigationService.update(navigation);
    }
    /**
     * 导航栏分页
     */
    @RequestMapping(value = "/bar/getList", method = RequestMethod.GET)
    public Map<String, Object> getBarList(Integer navigationId, Integer offset, Integer limit){
        return navigationBarService.getList(navigationId, offset, limit);
    }

    /**
     * 隐藏导航栏
     */
    @RequestMapping(value = "/bar/hidden", method = RequestMethod.POST)
    public ResponseEntity<Integer> hiddenBar(Integer id){
        return navigationBarService.setStatus(id, false);
    }
    /**
     * 隐藏导航栏
     */
    @RequestMapping(value = "/bar/show", method = RequestMethod.POST)
    public ResponseEntity<Integer> showBar(Integer id){
        return navigationBarService.setStatus(id, true);
    }
    /**
     * 更新导航栏
     */
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
    @RequestMapping(value = "/bar/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> deleteBar(Integer id){
        return navigationBarService.delete(id);
    }
    /**
     * 创建导航栏
     */
    @RequestMapping(value = "/bar/create", method = RequestMethod.POST)
    public ResponseEntity<Integer> createBar(@Validated NavigationBar navigationBar, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return navigationBarService.create(navigationBar);
    }
}
